package com.z20let.mitigia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {
    @Id
    private Integer projectId;
    private String licensePlate;
    private Integer vehicleId;
    private Date startDate;
    private Date endDate;
    private Integer startOdometer;
    private Integer endOdometer;
}
