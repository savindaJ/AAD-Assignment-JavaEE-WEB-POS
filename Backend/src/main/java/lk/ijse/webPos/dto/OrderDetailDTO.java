package lk.ijse.webPos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @author : savindaJ
 * @date : 1/14/2024
 * @since : 0.1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDTO {
    private String orderId;
    private String customerId;
    private String itemCode;
    private Integer qty;
    private Date date;
}
