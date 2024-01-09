package lk.ijse.webPos.controller.servlet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
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
        resp.setContentType("application/json");
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
        id = req.getParameter("id");
        name = req.getParameter("name");
        address = req.getParameter("address");
        salary = Double.valueOf(req.getParameter("salary"));
        String ok;
        RespMessage<Customer> message = new RespMessage<>();
        try {
            if (customerBO.saveCustomer(new CustomerDTO(id, name, address, salary))) {
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
        resp.setContentType("application/json");
        resp.getWriter().write(ok);
    }

    private boolean setValues(HttpServletRequest req) throws IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        try {
            id = jsonObject.getString("id");
            name = jsonObject.getString("name");
            address = jsonObject.getString("address");
            salary = Double.valueOf(String.valueOf(jsonObject.getJsonNumber("salary")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("put");
        if (setValues(req)) {
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
            resp.setContentType("application/json");
            resp.getWriter().println(ok);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = req.getParameter("id");
        RespMessage<Customer> message = new RespMessage<>();
        String ok;
        if (customerBO.deleteCustomer(id)) {
            ok = message.createMassage("OK", "Customer Delete Successfully !", null);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            ok = message.createMassage("NOT", "Customer Not Deleted !", null);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        resp.setContentType("application/json");
        resp.getWriter().write(ok);
    }
}
