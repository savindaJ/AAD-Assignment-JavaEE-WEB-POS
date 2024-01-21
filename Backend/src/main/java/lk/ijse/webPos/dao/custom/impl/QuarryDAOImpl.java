package lk.ijse.webPos.dao.custom.impl;

import lk.ijse.webPos.dao.custom.QuarryDAO;
import lk.ijse.webPos.dto.OrderDetailDTO;
import lk.ijse.webPos.dto.StatusDTO;
import lk.ijse.webPos.entity.Customer;
import lk.ijse.webPos.entity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/14/2024
 * @since : 0.1.0
 **/
public class QuarryDAOImpl implements QuarryDAO {

    private Session session;

    @Override
    public ArrayList<OrderDetailDTO> getAllOrderDetails() {
        ArrayList<OrderDetailDTO> details = new ArrayList<>();
        Transaction transaction = session.beginTransaction();
        List<Object[]> list = session.createNativeQuery("select\n" +
                "    o.order_id ,\n" +
                "    o.customer_id ,\n" +
                "    od.item_code,\n" +
                "    od.order_quantity,\n" +
                "    o.order_date\n" +
                "from orders o\n" +
                "JOIN\n" +
                "order_detail od\n" +
                "ON o.order_id = od.order_id ORDER BY o.order_date DESC LIMIT 40").list();
        transaction.commit();
        for (Object[] objects : list) {
            details.add(new OrderDetailDTO(
                    (String) objects[0],
                    (String) objects[1],
                    (String) objects[2],
                    (int) objects[3],
                    (Timestamp) objects[4]
            ));
        }
        return details;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public StatusDTO getStatus() {
        Transaction transaction = session.beginTransaction();
        BigInteger cusCount = (BigInteger) session.createNativeQuery("select count(*) from customer").getSingleResult();
        BigInteger itemCount = (BigInteger) session.createNativeQuery("select count(*) from item").getSingleResult();
        BigInteger detailCount = (BigInteger) session.createNativeQuery("select count(*) from order_detail").getSingleResult();
        BigInteger orderCount = (BigInteger) session.createNativeQuery("select count(*) from orders").getSingleResult();
        Double income = (Double) session.createNativeQuery("select sum(od.order_quantity * i.item_price) from order_detail od\n" +
                "JOIN\n" +
                "item i\n" +
                "ON od.item_code = i.item_code").getSingleResult();
        transaction.commit();
        return new StatusDTO(cusCount, orderCount, itemCount, detailCount, income);
    }
}
