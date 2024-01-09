package lk.ijse.webPos.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.webPos.bo.BOFactory;
import lk.ijse.webPos.bo.custom.ItemBO;
import lk.ijse.webPos.dto.CustomerDTO;
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
        if (list!=null){
            state = message.createMassage("OK", "Item Successfully Saved !", list);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }else {
            state = message.createMassage("NOT", "Item Not Saved !", null);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        resp.getWriter().write(state);
    }
}
