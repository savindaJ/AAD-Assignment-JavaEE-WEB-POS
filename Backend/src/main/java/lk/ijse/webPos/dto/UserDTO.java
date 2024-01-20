package lk.ijse.webPos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : savindaJ
 * @date : 1/20/2024
 * @since : 0.1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
