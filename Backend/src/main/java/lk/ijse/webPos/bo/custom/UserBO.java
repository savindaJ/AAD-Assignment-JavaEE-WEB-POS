package lk.ijse.webPos.bo.custom;

import lk.ijse.webPos.bo.SuperBO;
import lk.ijse.webPos.dto.UserDTO;

/**
 * @author : savindaJ
 * @date : 1/21/2024
 * @since : 0.1.0
 **/
public interface UserBO extends SuperBO {
    boolean addUser(UserDTO userDTO) throws Exception;

    boolean checkUser(UserDTO userDTO);
}
