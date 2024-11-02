package com.z20let.mitigia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
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
    private Long vehicleId;
    private Date startDate;
    private OffsetDateTime endDate;
    private Integer startOdometer;
    private Integer endOdometer;

    public Integer getOdometer() {
        return endOdometer;
    }

    public void setOdometer(Integer odometer) {
        this.endOdometer = odometer;
    }

    public OffsetDateTime getDate() {
        return endDate;
    }

    public void setDate(OffsetDateTime date) {
        this.endDate = date;
    }

    public void setEndDate(Date dateCellValue) {
        this.endDate = dateCellValue.toInstant().atOffset(OffsetDateTime.now().getOffset());
    }
}
