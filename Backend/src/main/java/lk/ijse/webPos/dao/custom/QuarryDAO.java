package lk.ijse.webPos.dao.custom;

import lk.ijse.webPos.dao.SuperDAO;
import lk.ijse.webPos.dto.OrderDetailDTO;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 * @author : savindaJ
 * @date : 1/14/2024
 * @since : 0.1.0
 **/
public interface QuarryDAO extends SuperDAO {
    ArrayList<OrderDetailDTO> getAllOrderDetails();

    void setSession(Session session);
}
