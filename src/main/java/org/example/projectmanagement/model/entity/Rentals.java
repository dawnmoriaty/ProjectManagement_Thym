package org.example.projectmanagement.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
