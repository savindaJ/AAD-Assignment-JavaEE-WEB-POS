package lk.ijse.webPos.dao;

import org.hibernate.Session;

import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
public interface CrudDAO <T,J> extends SuperDAO{
    boolean save(T entity) throws Exception;
    boolean update(T dto) throws Exception;
    boolean delete(J id);
    List<T> getAll();
    T getItem(J id);
    void setSession(Session session);
}
