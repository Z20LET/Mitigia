package com.z20let.mitigia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    private Long id;
    private int year;
    private String make;
    private String model;
    private String type;
    private String fuelType;
    private int energyConsumptionWLTP;
    private int energyConsumptionNEDC;
}
