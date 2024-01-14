package lk.ijse.webPos.api.servlet;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.webPos.bo.BOFactory;
import lk.ijse.webPos.bo.custom.OrderBO;
import lk.ijse.webPos.dto.OrderDTO;
import lk.ijse.webPos.util.RespMessage;

import java.io.IOException;

/**
 * @author : savindaJ
 * @date : 1/9/2024
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/order")
public class OrdersServlet extends HttpServlet {

    private OrderBO orderBO;

    @Override
    public void init() throws ServletException {
        orderBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.getWriter().write(new RespMessage<>().createMassage("NOT", nextOrderID(), null));
    }

    private String nextOrderID() {
        return orderBO.getLastOrderId();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        OrderDTO orderDTO = jsonb.fromJson(req.getReader(), OrderDTO.class);

        RespMessage<OrderDTO> message = new RespMessage<>();
        String ok;
        try {
            if (orderBO.placeOrder(orderDTO)) {
                ok = message.createMassage("OK", "Order Placed Successfully !", null);
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                ok = message.createMassage("NOT", "Order Not Placed !", null);
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            ok = message.createMassage("NOT", e.getLocalizedMessage(), null);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        resp.getWriter().write(ok);
    }
}

/*Session session = Configure.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);

        List<OrderDetail> orderDetails = new ArrayList<>();

        for (ItemDTO itemDTO : itemList) {
            OrderDetail orderDetail = new OrderDetail(new OrderDetailPK(orderId,itemDTO.getItemCode()), itemDTO.getQuantity());
            orderDetails.add(orderDetail);
        }

        Orders orders = new Orders(orderId,customer, orderDetails);
        session.save(orders);
        transaction.commit();
        session.close();*/





