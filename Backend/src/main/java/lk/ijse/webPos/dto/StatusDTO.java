package lk.ijse.webPos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author : savindaJ
 * @date : 1/15/2024
 * @since : 0.1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatusDTO implements Serializable {
    private BigInteger cusCount;
    private BigInteger orderCount;
    private BigInteger itemCount;
    private BigInteger orderDetailCount;
    private Double income;
}
