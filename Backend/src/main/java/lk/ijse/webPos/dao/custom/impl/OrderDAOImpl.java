package lk.ijse.webPos.dao.custom.impl;

import lk.ijse.webPos.dao.custom.OrderDAO;
import lk.ijse.webPos.entity.Orders;
import org.hibernate.Session;

import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/13/2024
 * @since : 0.1.0
 **/
public class OrderDAOImpl implements OrderDAO {

    private Session session;

    @Override
    public boolean save(Orders dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(Orders dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Orders> getAll() {
        return null;
    }

    @Override
    public Orders getItem(String id) {
        return null;
    }

    @Override
    public void setSession(Session session) {

    }
}
