package lk.ijse.webPos.entity;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
@Entity
@Table(name = "Item")
@AllArgsConstructor
public class Item {
    @Id
    @Column(name = "item_code" ,length = 50)
    private String itemCode;
    @Column(name = "item_description" ,length = 50)
    private String description;
    @Column(name = "item_price")
    private Double unitPrice;
    @Column(name = "item_quantity")
    private Integer qty;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "item")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Item() {

    }
}
