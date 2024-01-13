package lk.ijse.webPos.dao.custom.impl;

import lk.ijse.webPos.config.Configure;
import lk.ijse.webPos.dao.custom.CustomerDAO;
import lk.ijse.webPos.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
        session.close();
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
        }finally {
            session.close();
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
        }finally {
            session.close();
        }
    }

    @Override
    public List<Customer> getAll() {
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
        query.from(Customer.class);
        List<Customer> resultList = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public Customer getItem(String id) {
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        transaction.commit();
        return customer;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
