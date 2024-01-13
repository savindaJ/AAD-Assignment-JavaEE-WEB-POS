package lk.ijse.webPos.api.servlet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.webPos.bo.BOFactory;
import lk.ijse.webPos.bo.custom.ItemBO;
import lk.ijse.webPos.dto.ItemDTO;
import lk.ijse.webPos.util.RespMessage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : savindaJ
 * @date : 1/9/2024
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {

    private ItemBO itemBO;

    @Override
    public void init() throws ServletException {
        itemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<ItemDTO> list = itemBO.getAllItems();
        RespMessage<ItemDTO> message = new RespMessage<>();
        String state;
        resp.setContentType("application/json");
        if (list != null) {
            state = message.createMassage("OK", "Item Successfully Loaded !", list);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            state = message.createMassage("NOT", "Item Not Load !", null);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        resp.getWriter().write(state);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemCode = req.getParameter("itemCode");
        String description = req.getParameter("description");
        String unitPrice = req.getParameter("unitPrice");
        String qty = req.getParameter("qty");
        System.out.println(qty + " " + unitPrice + " " + description + " " + itemCode);
        ItemDTO itemDTO = new ItemDTO(itemCode, description, Double.parseDouble(unitPrice), Integer.parseInt(qty));
        RespMessage<ItemDTO> message = new RespMessage<>();
        String state = null;
        resp.setContentType("application/json");
        boolean b = false;
        try {
            if (itemBO.saveItem(itemDTO)) {
                state = message.createMassage("OK", "Item Successfully Saved !", null);
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                state = message.createMassage("NOT", "Item Not Saved !", null);
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            state = message.createMassage("NOT", e.getLocalizedMessage(), null);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        resp.getWriter().write(state);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = Json.createReader(req.getReader()).readObject();
        String itemCode = jsonObject.getString("itemCode");
        String description = jsonObject.getString("description");
        Double unitPrice = jsonObject.getJsonNumber("unitPrice").doubleValue();
        Integer qty = jsonObject.getJsonNumber("qty").intValue();

        System.out.println(qty + " " + unitPrice + " " + description + " " + itemCode);

        RespMessage<ItemDTO> message = new RespMessage<>();
        String state = null;
        resp.setContentType("application/json");
        try {
            if (itemBO.updateItem(new ItemDTO(itemCode, description,unitPrice,qty))) {
                state = message.createMassage("OK", "Item Successfully Updated !", null);
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                state = message.createMassage("NOT", "Item Not Updated !", null);
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            state = message.createMassage("NOT", e.getLocalizedMessage(), null);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        resp.getWriter().write(state);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemCode = req.getParameter("itemCode");
        RespMessage<ItemDTO> message = new RespMessage<>();
        String state = null;
        resp.setContentType("application/json");
        try {
            if (itemBO.deleteItem(itemCode)) {
                state = message.createMassage("OK", "Item Successfully Deleted !", null);
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                state = message.createMassage("NOT", "Item Not Deleted !", null);
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            state = message.createMassage("NOT", e.getLocalizedMessage(), null);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        resp.getWriter().write(state);
    }
}
