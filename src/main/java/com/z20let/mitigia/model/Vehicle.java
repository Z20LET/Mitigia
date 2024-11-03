package com.z20let.mitigia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {
    @Id
    private Long vehicleId;
    private String licensePlate;
    private String make;
    private String model;
    private String type;
    private String fuelType;
    private String year;
    private Integer mileage;
    private Integer energyConsumptionWLTP;
    private Integer energyConsumptionNEDC;
}