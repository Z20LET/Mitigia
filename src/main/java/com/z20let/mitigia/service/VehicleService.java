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

    public void deleteVehicleBy(String text) {
        Long id = Long.valueOf(text);
        vehicleRepository.deleteById(id);
    }

    public Vehicle addLicensePlate(Long id, String licensePlate) {
        Optional<Vehicle> existingVehicleOpt = vehicleRepository.findById(id);
        Vehicle vehicle = new Vehicle();
        if (existingVehicleOpt.isPresent()) {
            vehicle = existingVehicleOpt.get();
        }
        vehicle.setLicensePlate(licensePlate);
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> findById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }
}