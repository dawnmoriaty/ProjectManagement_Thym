package org.example.projectmanagement.model.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCalculationDTO {
    private Long vehicleId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rentalDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
    private double dailyRate;
    private int rentalDays;
    private double totalRental;
    private double depositAmount;
}

