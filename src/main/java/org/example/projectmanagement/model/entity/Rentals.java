package org.example.projectmanagement.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rentals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicles vehicle;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
    @Temporal(TemporalType.DATE)
    private Date rentalDate;
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    private double rentalPrice;
    private String status;
    private double deposit;
    private String note;
}
