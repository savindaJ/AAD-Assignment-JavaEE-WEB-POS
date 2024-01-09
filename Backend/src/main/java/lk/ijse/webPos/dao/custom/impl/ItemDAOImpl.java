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
    public boolean save(Item dto) {
        return false;
    }

    @Override
    public boolean update(Item dto) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
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
        return null;
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }
}
