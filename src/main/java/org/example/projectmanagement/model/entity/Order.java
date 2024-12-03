package org.example.projectmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Users users;
    @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> items = new ArrayList<>();
    private double totalPrice;
    private String status;
    private String phone;
    private String address;
    private String email;
}
