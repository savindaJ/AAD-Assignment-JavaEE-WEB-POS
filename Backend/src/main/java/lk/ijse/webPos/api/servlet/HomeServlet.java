package lk.ijse.webPos.api.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.webPos.bo.BOFactory;
import lk.ijse.webPos.bo.custom.OrderDetailBO;
import lk.ijse.webPos.dto.StatusDTO;

import java.io.IOException;

/**
 * @author : savindaJ
 * @date : 1/15/2024
 * @since : 0.1.0
 **/
@WebServlet(name = "HomeServlet", urlPatterns = "/")
public class HomeServlet extends HttpServlet {

    private OrderDetailBO orderDetailBO;

    @Override
    public void init() throws ServletException {
        orderDetailBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDERDETAIL);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatusDTO status = orderDetailBO.getStatus();
        System.out.println(status);
    }
}
