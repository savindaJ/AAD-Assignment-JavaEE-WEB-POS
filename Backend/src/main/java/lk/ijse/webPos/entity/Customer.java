package lk.ijse.webPos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
@Entity
@Table(schema = "customer")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @Column(name = "customer_id")
    private String cusId;
    @Column(name = "customer_name")
    private String name;
    @Column(name = "customer_address")
    private String address;
    @Column(name = "customer_salary")
    private Double salary;
}
