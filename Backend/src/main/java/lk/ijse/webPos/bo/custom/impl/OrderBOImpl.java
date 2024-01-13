package lk.ijse.webPos.bo.custom.impl;

import lk.ijse.webPos.bo.custom.OrderBO;
import lk.ijse.webPos.config.Configure;
import lk.ijse.webPos.dao.DAOFactory;
import lk.ijse.webPos.dao.custom.ItemDAO;
import lk.ijse.webPos.dao.custom.OrderDAO;
import lk.ijse.webPos.dto.OrderDTO;
import org.hibernate.Session;

/**
 * @author : savindaJ
 * @date : 1/13/2024
 * @since : 0.1.0
 **/
public class OrderBOImpl implements OrderBO {
    private final OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERDAO);

    private final ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEMDAO);
    @Override
    public boolean placeOrder(OrderDTO orderDTO) {
        return addOrder(orderDTO);
    }

    private boolean addOrder(OrderDTO orderDTO) {
        Session session = Configure.getInstance().getSession();
        orderDAO.setSession(session);
        return false;
    }
}
