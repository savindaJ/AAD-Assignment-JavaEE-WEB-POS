package lk.ijse.webPos.api.servlet;

import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.webPos.bo.BOFactory;
import lk.ijse.webPos.bo.custom.ItemBO;
import lk.ijse.webPos.dto.ItemDTO;
import lk.ijse.webPos.util.RespMessage;
import lk.ijse.webPos.util.ValidationUtil;

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
        ItemDTO itemDTO = JsonbBuilder.create().fromJson(req.getReader(), ItemDTO.class);
        if (ValidationUtil.validate(itemDTO.getItemCode(), itemDTO.getDescription(), itemDTO.getPrice(), itemDTO.getQuantity())) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(new RespMessage<>().createMassage("400", "Bad Request !", null));
            return;
        }
        RespMessage<ItemDTO> message = new RespMessage<>();
        String state = null;
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
        ItemDTO itemDTO = JsonbBuilder.create().fromJson(req.getReader(), ItemDTO.class);
        if (ValidationUtil.validate(itemDTO)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(new RespMessage<>().createMassage("400", "Bad Request !", null));
            return;
        }
        RespMessage<ItemDTO> message = new RespMessage<>();
        String state = null;
        try {
            if (itemBO.updateItem(itemDTO)) {
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
        if (ValidationUtil.validateItemID(itemCode)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(new RespMessage<>().createMassage("400", "Bad Request !", null));
            return;
        }
        RespMessage<ItemDTO> message = new RespMessage<>();
        String state = null;
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
