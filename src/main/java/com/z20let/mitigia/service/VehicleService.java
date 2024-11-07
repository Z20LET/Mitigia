package com.z20let.mitigia.service;

import com.z20let.mitigia.model.Vehicle;
import com.z20let.mitigia.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
@AllArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;


    public void saveVehicles(MultipartFile file) {
        if(ExcelToDatabaseService.validateExcelFile(file)) {
            try {
                List<Vehicle> vehicles = ExcelToDatabaseService.getVehicleData((file.getInputStream()));
                vehicleRepository.saveAll(vehicles);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

}
