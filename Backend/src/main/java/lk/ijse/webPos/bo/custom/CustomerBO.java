package lk.ijse.webPos.bo.custom;

import lk.ijse.webPos.bo.SuperBO;
import lk.ijse.webPos.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO customerDTO);

    boolean updateCustomer(CustomerDTO customerDTO);

    boolean deleteCustomer(String id);

    ArrayList<CustomerDTO> getAllCustomers();
}
