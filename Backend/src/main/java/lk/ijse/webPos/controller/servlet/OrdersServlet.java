package lk.ijse.webPos.controller.servlet;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.webPos.dto.OrderDTO;

import java.io.IOException;

/**
 * @author : savindaJ
 * @date : 1/9/2024
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/order")
public class OrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        OrderDTO orderDTO = jsonb.fromJson(req.getReader(), OrderDTO.class);
        System.out.println(orderDTO);
        /* {
   "orderId":"U005",
   "orderDate":"kamal",
   "customerId":"matara",
   "itemList" :[{
       "itemCode" : "I002",
       "description":"biscut",
       "price" : 230.2,
       "quantity":10
   },
   {
       "itemCode" : "I003",
       "description":"Lux",
       "price" : 12000.2,
       "quantity":3
   }]
}*/
    }
}
