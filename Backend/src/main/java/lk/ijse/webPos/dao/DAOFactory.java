package lk.ijse.webPos.dao;

import lk.ijse.webPos.dao.custom.impl.*;

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
        CUSTOMERDAO,ITEMDAO, ORDERDAO, QUERYDAO,USERDAO
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
            case USERDAO:
                return (T) new UserDAOImpl();
            default:
                return null;
        }
    }
}
