package lk.ijse.webPos.bo.custom;

import lk.ijse.webPos.bo.SuperBO;
import lk.ijse.webPos.dto.OrderDTO;

/**
 * @author : savindaJ
 * @date : 1/13/2024
 * @since : 0.1.0
 **/
public interface OrderBO extends SuperBO {
    boolean placeOrder(OrderDTO orderDTO) throws Exception;
}
