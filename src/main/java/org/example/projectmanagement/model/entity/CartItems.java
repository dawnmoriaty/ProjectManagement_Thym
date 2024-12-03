package org.example.projectmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
@Entity
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Vehicles vehicles;

    private int rentalHour;
    private double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Cart cart;
}
