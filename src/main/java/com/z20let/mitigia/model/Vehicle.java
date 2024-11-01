package com.z20let.mitigia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {
    @Id
    private Long vehicleId;
    private Integer vehicleYear;
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleType;
    private String fuelType;
    private Integer energyConsumptionWLTP;
    private Integer energyConsumptionNEDC;
    @Enumerated(EnumType.STRING)
    private ConsumptionType energyConsumptionType;

    public Integer getConsumptionType() {
        if (energyConsumptionWLTP != null) {
            energyConsumptionType = ConsumptionType.WLTP;
            return energyConsumptionWLTP;
        } else if (energyConsumptionNEDC != null) {
            energyConsumptionType = ConsumptionType.NEDC;
            return energyConsumptionNEDC;
        } else {
            energyConsumptionType = ConsumptionType.DEFAULT;
            return 188;
        }
    }
}
