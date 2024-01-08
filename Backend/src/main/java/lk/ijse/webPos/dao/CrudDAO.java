package lk.ijse.webPos.dao;

import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
public interface CrudDAO <T,J> extends SuperDAO{
    boolean save(T dto);
    boolean update(T dto);
    boolean delete(J id);
    List<T> getAll();
    T getItem(J id);
}
