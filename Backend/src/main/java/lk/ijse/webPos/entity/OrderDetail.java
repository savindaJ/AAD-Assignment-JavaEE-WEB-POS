package lk.ijse.webPos.entity;

import lk.ijse.webPos.embedded.OrderDetailPK;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
@Entity
@Table(name = "order_detail")
@Data
@AllArgsConstructor
@NamedEntityGraph
public class OrderDetail {
    @EmbeddedId
    private OrderDetailPK orderDetailPK;
    @Column(name = "order_quantity")
    private int qty;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", insertable = false, updatable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "item_code", referencedColumnName = "item_code", insertable = false, updatable = false)
    private Item item;

    public OrderDetail() {

    }

    public OrderDetail(OrderDetailPK orderDetailPK, Integer quantity) {
        this.orderDetailPK = orderDetailPK;
        this.qty = quantity;
    }
}
