package com.z20let.mitigia.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
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
