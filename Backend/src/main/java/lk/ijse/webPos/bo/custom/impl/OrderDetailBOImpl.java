package lk.ijse.webPos.bo.custom.impl;

import lk.ijse.webPos.bo.custom.OrderDetailBO;
import lk.ijse.webPos.config.Configure;
import lk.ijse.webPos.dao.DAOFactory;
import lk.ijse.webPos.dao.custom.QuarryDAO;
import lk.ijse.webPos.dto.OrderDetailDTO;

import java.util.ArrayList;

/**
 * @author : savindaJ
 * @date : 1/14/2024
 * @since : 0.1.0
 **/
public class OrderDetailBOImpl implements OrderDetailBO {

    private final QuarryDAO quarryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public ArrayList<OrderDetailDTO> getAllOrderDetails() {
        quarryDAO.setSession(Configure.getInstance().getSession());
        return quarryDAO.getAllOrderDetails();
    }
}
