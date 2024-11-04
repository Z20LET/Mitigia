package com.z20let.mitigia.controller;

import com.z20let.mitigia.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    @DeleteMapping("delete-vehicle-by-id")
    public void deleteVehicle(@RequestParam("id") Long id){
        vehicleService.deleteVehicleBy(id);
    }
}
