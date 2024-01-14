package lk.ijse.webPos.dao.custom.impl;

import lk.ijse.webPos.dao.custom.QuarryDAO;
import lk.ijse.webPos.dto.OrderDetailDTO;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 * @author : savindaJ
 * @date : 1/14/2024
 * @since : 0.1.0
 **/
public class QuarryDAOImpl implements QuarryDAO {

    private Session session;

    @Override
    public ArrayList<OrderDetailDTO> getAllOrderDetails() {
        return null;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
