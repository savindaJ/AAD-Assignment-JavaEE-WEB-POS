package lk.ijse.webPos.bo.custom;

import lk.ijse.webPos.bo.SuperBO;
import lk.ijse.webPos.dto.OrderDetailDTO;
import lk.ijse.webPos.dto.StatusDTO;

import java.util.ArrayList;

/**
 * @author : savindaJ
 * @date : 1/14/2024
 * @since : 0.1.0
 **/
public interface OrderDetailBO extends SuperBO {
    ArrayList<OrderDetailDTO> getAllOrderDetails();

    StatusDTO getStatus();
}
