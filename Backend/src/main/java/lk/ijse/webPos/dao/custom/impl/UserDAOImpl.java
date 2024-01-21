package lk.ijse.webPos.dao.custom.impl;

import lk.ijse.webPos.dao.custom.UserDAO;
import lk.ijse.webPos.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/21/2024
 * @since : 0.1.0
 **/
public class UserDAOImpl implements UserDAO {

    private Session session;

    @Override
    public boolean save(User entity) throws Exception {
        Transaction transaction = session.beginTransaction();
        Serializable save = session.save(entity);
        transaction.commit();
        session.close();
        return save != null;
    }

    @Override
    public boolean update(User dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getItem(String id) {
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
