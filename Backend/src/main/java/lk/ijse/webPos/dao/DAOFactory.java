package lk.ijse.webPos.dao;

import lk.ijse.webPos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.webPos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.webPos.dao.custom.impl.OrderDAOImpl;
import lk.ijse.webPos.dao.custom.impl.QuarryDAOImpl;

import java.util.Stack;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
public class DAOFactory {
    private static DAOFactory factory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return factory == null ? new DAOFactory() : factory;
    }

    public enum DAOTypes {
        CUSTOMERDAO,ITEMDAO, ORDERDAO, QUERYDAO
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMERDAO:
                return (T) new CustomerDAOImpl();
            case ITEMDAO:
                return (T) new ItemDAOImpl();
            case ORDERDAO:
                return (T) new OrderDAOImpl();
            case QUERYDAO:
                return (T) new QuarryDAOImpl();
            default:
                return null;
        }
    }
}
