package lk.ijse.webPos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
public class Customer implements Serializable {
    @Id
    @Column(name = "customer_id", length = 50)
    private String cusId;
    @Column(name = "customer_name", length = 50)
    private String name;
    @Column(name = "customer_address", length = 50)
    private String address;
    @Column(name = "customer_salary")
    private Double salary;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Orders> orders = new ArrayList<>();

    public Customer(String cusId, String name, String address, Double salary) {
        this.cusId = cusId;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }
}
