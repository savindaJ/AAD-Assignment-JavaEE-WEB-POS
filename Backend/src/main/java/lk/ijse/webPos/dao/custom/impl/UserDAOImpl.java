package lk.ijse.webPos.dao.custom.impl;

import lk.ijse.webPos.dao.custom.UserDAO;
import lk.ijse.webPos.entity.User;
import org.hibernate.Session;

import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/21/2024
 * @since : 0.1.0
 **/
public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User dto) throws Exception {
        return false;
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
        return null;
    }

    @Override
    public void setSession(Session session) {

    }
}
