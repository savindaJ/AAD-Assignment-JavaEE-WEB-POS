package lk.ijse.webPos.api.servlet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.webPos.bo.BOFactory;
import lk.ijse.webPos.bo.custom.CustomerBO;
import lk.ijse.webPos.dto.CustomerDTO;
import lk.ijse.webPos.entity.Customer;
import lk.ijse.webPos.util.RespMessage;
import lk.ijse.webPos.util.ValidationUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/customer", loadOnStartup = 1)
public class CustomerServlet extends HttpServlet {

    private CustomerBO customerBO;

    private String id;
    private String name;
    private String address;
    private Double salary;

    @Override
    public void init() throws ServletException {
        customerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<CustomerDTO> list = customerBO.getAllCustomers();
        RespMessage<CustomerDTO> message = new RespMessage<>();
        String ok;
        if (list != null) {
            ok = message.createMassage("OK", "Customers Load Successfully !", list);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            ok = message.createMassage("NOT", "Customers Not Loaded !", null);
        }
        resp.getWriter().write(ok);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDTO customerDTO = JsonbBuilder.create().fromJson(req.getReader(), CustomerDTO.class);
        System.out.println(customerDTO);
        if (ValidationUtil.validate(customerDTO)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(new RespMessage<>().createMassage("400", "Bad Request !", null));
            return;
        }

        String ok;
        RespMessage<Customer> message = new RespMessage<>();
        try {
            if (customerBO.saveCustomer(customerDTO)) {
                ok = message.createMassage("OK", "Customer Saved Successfully !", null);
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                ok = message.createMassage("NOT", "Customer Not Saved !", null);
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            ok = message.createMassage("NOT", e.getLocalizedMessage(), null);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        resp.getWriter().write(ok);
    }

    private boolean setValues(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CustomerDTO customerDTO = JsonbBuilder.create().fromJson(req.getReader(), CustomerDTO.class);

        try {
            if (ValidationUtil.validate(customerDTO)) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write(new RespMessage<>().createMassage("400", "Bad Request !", null));
                return false;
            }else {
                id = customerDTO.getCusId();
                name = customerDTO.getCusName();
                address = customerDTO.getAddress();
                salary = customerDTO.getSalary();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (setValues(req, resp)) {
            RespMessage<Customer> message = new RespMessage<>();
            String ok;
            try {
                if (customerBO.updateCustomer(new CustomerDTO(id, name, address, salary))) {
                    ok = message.createMassage("OK", "Customer Updated Successfully !", null);
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                } else {
                    ok = message.createMassage("NOT", "Customer Not Updated !", null);
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            } catch (Exception e) {
                ok = message.createMassage("NOT", e.getLocalizedMessage(), null);
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            resp.getWriter().println(ok);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = req.getParameter("id");
        if (ValidationUtil.validateCusID(id)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(new RespMessage<>().createMassage("400", "Bad Request !", null));
            return;
        }

        RespMessage<Customer> message = new RespMessage<>();
        String ok;
        if (customerBO.deleteCustomer(id)) {
            ok = message.createMassage("OK", "Customer Delete Successfully !", null);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            ok = message.createMassage("NOT", "Customer Not Deleted !", null);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        resp.getWriter().write(ok);
    }
}
