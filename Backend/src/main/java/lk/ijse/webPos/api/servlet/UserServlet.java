package lk.ijse.webPos.api.servlet;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.webPos.dto.UserDTO;

import java.io.IOException;

/**
 * @author : savindaJ
 * @date : 1/20/2024
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do post");
        Jsonb jsonb = JsonbBuilder.create();
        UserDTO userDTO = jsonb.fromJson(req.getReader(), UserDTO.class);
        System.out.println(userDTO);
        resp.getWriter().println("Hello");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do get");
        System.out.println("do post");
        Jsonb jsonb = JsonbBuilder.create();
        String json = jsonb.toJson(req.getReader(), Object.class);
        System.out.println(json);
    }
}
