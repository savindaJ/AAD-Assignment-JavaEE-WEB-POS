package lk.ijse.webPos.config;

import lk.ijse.webPos.entity.Customer;
import lk.ijse.webPos.entity.Item;
import lk.ijse.webPos.entity.OrderDetail;
import lk.ijse.webPos.entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.Order;
import java.io.IOException;
import java.util.Properties;

public class Configure {
    private static Configure configure;
    private static final SessionFactory factory;

    static {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.Properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        configuration
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Orders.class)
                .addAnnotatedClass(OrderDetail.class);

        factory=configuration.setProperties(properties).buildSessionFactory();
    }

    private Configure(){}

    public static Configure getInstance(){
        return configure == null ? new Configure() : configure;
    }

    public Session getSession(){
        return factory.openSession();
    }
}
