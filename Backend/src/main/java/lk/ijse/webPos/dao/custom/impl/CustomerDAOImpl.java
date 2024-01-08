package lk.ijse.webPos.dao.custom.impl;

import lk.ijse.webPos.config.Configure;
import lk.ijse.webPos.dao.custom.CustomerDAO;
import lk.ijse.webPos.entity.Customer;
import org.hibernate.Session;

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
        System.out.println(entity);
        System.out.println(session);
        return true;
    }

    @Override
    public boolean update(Customer dto) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
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
