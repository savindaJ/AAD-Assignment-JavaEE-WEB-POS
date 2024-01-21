package lk.ijse.webPos.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Embeddable
public class OrderDetailPK implements Serializable {

    @Column(name = "order_id", length = 50)
    private String orderId;
    @Column(name = "item_code", length = 50)
    private String itemId;

    public OrderDetailPK() {

    }
}
