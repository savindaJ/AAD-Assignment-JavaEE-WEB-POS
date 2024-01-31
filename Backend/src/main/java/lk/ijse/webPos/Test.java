package lk.ijse.webPos;

import lk.ijse.webPos.dto.CustomerDTO;
import lk.ijse.webPos.dto.ItemDTO;
import lk.ijse.webPos.util.ValidationUtil;

/**
 * @author : savindaJ
 * @date : 1/31/2024
 * @since : 0.1.0
 **/
public class Test {
    public static void main(String[] args) {
        boolean validate = ValidationUtil.validate(new CustomerDTO("C00-001", "Savinda", "matara", null));
//        boolean validate = ValidationUtil.validate(new ItemDTO("I00-001", "biscuts", 1213.2, 12));
        System.out.println(validate);
    }
}
