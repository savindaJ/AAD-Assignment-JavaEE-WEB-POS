package lk.ijse.webPos.dao;

import lk.ijse.webPos.dao.custom.impl.CustomerDAOImpl;

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
        CUSTOMERDAO
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMERDAO:
                return (T) new CustomerDAOImpl();
            default:
                return null;
        }
    }
}