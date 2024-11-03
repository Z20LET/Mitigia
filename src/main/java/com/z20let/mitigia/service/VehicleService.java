package com.z20let.mitigia.service;

import com.z20let.mitigia.model.Vehicle;
import com.z20let.mitigia.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    public void deleteVehicleBy(Long id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle updateLicensePlate(Long id, String licensePlate) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            vehicle.setLicensePlate(licensePlate);
            return vehicleRepository.save(vehicle);
        } else {
            throw new RuntimeException("Vehicle not found with id: " + id);
        }
    }

}
