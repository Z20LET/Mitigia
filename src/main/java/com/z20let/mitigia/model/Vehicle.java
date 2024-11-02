package com.z20let.mitigia.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

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
    private String year;
    private Integer mileage;
    private Integer energyConsumptionWLTP;
    private Integer energyConsumptionNEDC;
    @Getter
    @Setter
    @OneToMany(mappedBy = "vehicle")
    private Collection<OdometerReading> odometerReading;

}