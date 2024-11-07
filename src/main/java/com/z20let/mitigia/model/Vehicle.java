package com.z20let.mitigia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.Year;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    private long vehicleId;
    private int vehicleYear;
    private String vehicleManufacturer;
    private String vehicleModel;
    private String vehicleType;
    private String fuelType;
    private int energyConsumptionWLTP;
    private int energyConsumptionNEDC;
}
