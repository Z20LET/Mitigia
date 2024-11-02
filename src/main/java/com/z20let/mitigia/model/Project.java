
package com.z20let.mitigia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer startMileage;
    private Integer endMileage;
    private Double carbonEmission;
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    Vehicle vehicle;
}
