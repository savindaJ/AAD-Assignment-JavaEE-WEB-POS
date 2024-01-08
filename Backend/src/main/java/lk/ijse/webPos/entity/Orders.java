package lk.ijse.webPos.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @Column(name = "order_id",length = 50)
    private String orderID;
    @Column(name = "order_date")
    private Timestamp date;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "orders")
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
