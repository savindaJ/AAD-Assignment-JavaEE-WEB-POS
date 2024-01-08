import lk.ijse.webPos.config.Configure;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
public class Test {
    public static void main(String[] args) {
        Session session = Configure.getInstance().getSession();
    }
}
