package org.example.projectmanagement.model.dtos.request;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleRequestDTO {
    private Long id;
    private String name;
    private String licensePlate;
    private String manufacturer;
    private String model;
    private MultipartFile imageVehicle;
    private String description;
    private String status;
    private Double price;
    private Long categoryID;
}
