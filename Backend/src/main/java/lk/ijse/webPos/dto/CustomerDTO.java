package lk.ijse.webPos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private String cusId;
    private String cusName;
    private String address;
    private Double salary;
}
