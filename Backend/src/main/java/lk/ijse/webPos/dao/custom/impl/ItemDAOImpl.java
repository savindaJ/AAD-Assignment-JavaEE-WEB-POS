package lk.ijse.webPos.dao.custom.impl;

import lk.ijse.webPos.dao.custom.ItemDAO;
import lk.ijse.webPos.entity.Customer;
import lk.ijse.webPos.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/9/2024
 * @since : 0.1.0
 **/
public class ItemDAOImpl implements ItemDAO {

    private Session session;

    @Override
    public boolean save(Item entity) throws Exception {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e) {
            throw Exception.class.cast(e);
        }
    }

    @Override
    public boolean update(Item entity) throws Exception {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
//            session.close();
            return true;
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            Transaction transaction = session.beginTransaction();
            Item item = session.get(Item.class, id);
            session.delete(item);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Item> getAll() {
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Item> query = criteriaBuilder.createQuery(Item.class);
        query.from(Item.class);
        List<Item> resultList = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public Item getItem(String id) {
        Transaction transaction = session.beginTransaction();
        Item item = session.get(Item.class, id);
        transaction.commit();
        return item;
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }
}
