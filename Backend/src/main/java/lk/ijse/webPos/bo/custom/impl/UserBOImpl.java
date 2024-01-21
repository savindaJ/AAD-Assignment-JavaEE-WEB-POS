package lk.ijse.webPos.bo.custom.impl;

import lk.ijse.webPos.bo.custom.UserBO;
import lk.ijse.webPos.config.Configure;
import lk.ijse.webPos.dao.DAOFactory;
import lk.ijse.webPos.dao.custom.UserDAO;
import lk.ijse.webPos.dto.UserDTO;
import lk.ijse.webPos.entity.User;

/**
 * @author : savindaJ
 * @date : 1/21/2024
 * @since : 0.1.0
 **/
public class UserBOImpl implements UserBO {

    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USERDAO);

    @Override
    public boolean addUser(UserDTO userDTO) throws Exception {
        userDAO.setSession(Configure.getInstance().getSession());
        return userDAO.save(new User(userDTO.getEmail(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getPassword()));
    }
}
