package lk.ijse.webPos.bo.custom.impl;

import lk.ijse.webPos.bo.custom.OrderBO;
import lk.ijse.webPos.config.Configure;
import lk.ijse.webPos.dao.DAOFactory;
import lk.ijse.webPos.dao.custom.CustomerDAO;
import lk.ijse.webPos.dao.custom.ItemDAO;
import lk.ijse.webPos.dao.custom.OrderDAO;
import lk.ijse.webPos.dto.OrderDTO;
import lk.ijse.webPos.embedded.OrderDetailPK;
import lk.ijse.webPos.entity.Customer;
import lk.ijse.webPos.entity.Item;
import lk.ijse.webPos.entity.OrderDetail;
import lk.ijse.webPos.entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/13/2024
 * @since : 0.1.0
 **/
public class OrderBOImpl implements OrderBO {
    private final OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERDAO);

    private final ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEMDAO);

    private final CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMERDAO);

    @Override
    public boolean placeOrder(OrderDTO orderDTO) throws Exception {
        return addOrder(orderDTO);
    }

    @Override
    public String getLastOrderId() {

        // ToDo : business logic

        orderDAO.setSession(Configure.getInstance().getSession());
        String lastOrderId = orderDAO.getLastOrderId();
        if (lastOrderId == null) {
            return "ORD-001";
        } else {

            int maxId = Integer.parseInt(lastOrderId.replace("ORD-", ""));

            maxId = maxId + 1;

            String id = "";
            if (maxId < 10) {
                id = "ORD-00" + maxId;
            } else if (maxId < 100) {
                id = "ORD-0" + maxId;
            } else {
                id = "ORD-" + maxId;
            }
            return id;
        }
    }

    private boolean addOrder(OrderDTO orderDTO) throws Exception {
        List<OrderDetail> orderDetails = new ArrayList<>();
        Session session = Configure.getInstance().getSession();

        orderDAO.setSession(session);
        customerDAO.setSession(session);
        itemDAO.setSession(session);

        Customer customer = customerDAO.getItem(orderDTO.getCustomerId());//ToDo : check this

        orderDTO.getItemList().forEach(itemDTO -> {
            orderDetails.add(new OrderDetail(new OrderDetailPK(orderDTO.getOrderId(), itemDTO.getItemCode()), itemDTO.getQuantity()));
            Item item = itemDAO.getItem(itemDTO.getItemCode()); //ToDo : check this
            item.setQty(item.getQty() - itemDTO.getQuantity()); //ToDo : check this
            try {
                itemDAO.update(item);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return orderDAO.save(new Orders(orderDTO.getOrderId(), customer, orderDetails));
    }
}
