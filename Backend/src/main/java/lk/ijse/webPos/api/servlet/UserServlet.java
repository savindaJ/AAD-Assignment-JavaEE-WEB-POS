package lk.ijse.webPos.api.servlet;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.webPos.bo.BOFactory;
import lk.ijse.webPos.bo.custom.UserBO;
import lk.ijse.webPos.dto.UserDTO;
import lk.ijse.webPos.util.RespMessage;

import java.io.IOException;

/**
 * @author : savindaJ
 * @date : 1/20/2024
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    private UserBO userBO;

    @Override
    public void init() throws ServletException {
        userBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        UserDTO userDTO = jsonb.fromJson(req.getReader(), UserDTO.class);

        if (userDTO.getEmail().isEmpty() || userDTO.getPassword().isEmpty() || userDTO.getFirstName().isEmpty() || userDTO.getLastName().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(new RespMessage().createMassage("400", "Bad Request !", null));
            return;
        }

        try {
            boolean isAdded = userBO.addUser(userDTO);
            if (isAdded) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write(new RespMessage().createMassage("200", "success", null));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                resp.getWriter().write(new RespMessage().createMassage("500", "fail", null));
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write(new RespMessage().createMassage("500", "Already Exits User Please Try Again !", null));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO = new UserDTO("", "", req.getParameter("email"), req.getParameter("password"));
        if (userDTO.getEmail().isEmpty() || userDTO.getPassword().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(new RespMessage().createMassage("400", "Bad Request !", null));
            return;
        }

        try {
            boolean isChecked = userBO.checkUser(userDTO);
            if (isChecked) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(new RespMessage().createMassage("200", "success", null));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write(new RespMessage().createMassage("500", "fail", null));
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write(new RespMessage().createMassage("500", e.getLocalizedMessage(), null));
        }
    }
}
