package lk.ijse.webPos.bo.custom.impl;

import lk.ijse.webPos.bo.custom.CustomerBO;
import lk.ijse.webPos.config.Configure;
import lk.ijse.webPos.dto.CustomerDTO;
import org.hibernate.Session;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
public class CustomerBOImpl implements CustomerBO {
    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        Session session = Configure.getInstance().getSession();
        System.out.println(session);
        System.out.println(customerDTO);
        return false;
    }
}
