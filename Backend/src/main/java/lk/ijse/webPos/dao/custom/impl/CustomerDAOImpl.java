package lk.ijse.webPos.dao.custom.impl;

import lk.ijse.webPos.config.Configure;
import lk.ijse.webPos.dao.custom.CustomerDAO;
import lk.ijse.webPos.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
public class CustomerDAOImpl implements CustomerDAO {

    private Session session;

    @Override
    public boolean save(Customer entity) {
        Transaction transaction = session.beginTransaction();
        Serializable save = session.save(entity);
        transaction.commit();
        return save != null;
    }

    @Override
    public boolean update(Customer entity) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.delete(customer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public Customer getItem(String id) {
        return null;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
