package com.z20let.mitigia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    private int id;
    private String licensePlate;
    private Long vehicleId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int startOdo;
    private int endOdo;
}
