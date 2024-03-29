package lk.ijse.webPos.bo.custom.impl;

import lk.ijse.webPos.bo.custom.CustomerBO;
import lk.ijse.webPos.config.Configure;
import lk.ijse.webPos.dao.DAOFactory;
import lk.ijse.webPos.dao.custom.CustomerDAO;
import lk.ijse.webPos.dto.CustomerDTO;
import lk.ijse.webPos.entity.Customer;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMERDAO);

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws Exception {
        customerDAO.setSession(Configure.getInstance().getSession());
        return customerDAO.save(new Customer(
                customerDTO.getCusId(),
                customerDTO.getCusName(),
                customerDTO.getAddress(),
                customerDTO.getSalary()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        customerDAO.setSession(Configure.getInstance().getSession());
        return customerDAO.update(new Customer(
                customerDTO.getCusId(),
                customerDTO.getCusName(),
                customerDTO.getAddress(),
                customerDTO.getSalary()
        ));
    }

    @Override
    public boolean deleteCustomer(String id) {
        customerDAO.setSession(Configure.getInstance().getSession());
        return customerDAO.delete(id);
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() {
        customerDAO.setSession(Configure.getInstance().getSession());
        ArrayList<CustomerDTO> list = new ArrayList<>();
        for (Customer customer : customerDAO.getAll()) {
            list.add(new CustomerDTO(
                    customer.getCusId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getSalary()
            ));
        }
        return list;
    }
}
