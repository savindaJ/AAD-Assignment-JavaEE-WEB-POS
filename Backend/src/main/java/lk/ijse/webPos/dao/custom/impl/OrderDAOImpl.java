package lk.ijse.webPos.dao.custom.impl;

import lk.ijse.webPos.dao.custom.OrderDAO;
import lk.ijse.webPos.entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/13/2024
 * @since : 0.1.0
 **/
public class OrderDAOImpl implements OrderDAO {

    private Session session;

    @Override
    public boolean save(Orders entity) throws Exception {
        try {
            Transaction transaction = session.beginTransaction();
            Serializable save = session.save(entity);
            transaction.commit();
            session.close();
            return save!=null;
        }catch (Exception e) {
            throw new Exception(e);
        }
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
        this.session = session;
    }

    @Override
    public String getLastOrderId() {
        return (String) session.createNativeQuery("SELECT order_id FROM Orders ORDER BY order_id DESC LIMIT 1").uniqueResult();
    }
}
