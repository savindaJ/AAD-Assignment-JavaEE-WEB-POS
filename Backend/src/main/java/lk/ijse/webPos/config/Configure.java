package lk.ijse.webPos.config;

import lk.ijse.webPos.entity.*;
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
        factory = new Configuration()
                .configure()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Orders.class)
                .addAnnotatedClass(OrderDetail.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    private Configure(){}

    public static Configure getInstance(){
        return configure == null ? new Configure() : configure;
    }

    public Session getSession(){
        return factory.openSession();
    }
}
