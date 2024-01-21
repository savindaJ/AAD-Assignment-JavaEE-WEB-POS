package lk.ijse.webPos.bo;

import lk.ijse.webPos.bo.custom.impl.*;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        return boFactory == null ? new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER, ITEM, ORDER, ORDERDETAIL, USER
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case ORDER:
                return (T) new OrderBOImpl();
            case ORDERDETAIL:
                return (T) new OrderDetailBOImpl();
            case USER:
                return (T) new UserBOImpl();
            default:
                return null;
        }
    }
}
