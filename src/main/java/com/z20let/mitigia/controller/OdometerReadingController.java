package com.z20let.mitigia.controller;

import com.z20let.mitigia.service.OdometerReadingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping( "/api/odometer")
public class OdometerReadingController {

    private OdometerReadingService odometerService;

    @PutMapping("/update-odometer")
    public ResponseEntity<?> updateOdometer(@RequestParam("licensePlate") String licensePlate,
                                            @RequestParam("odometer") String odometer,
                                            @RequestParam("date") String date){
        return ResponseEntity.ok(Map.of("Message", this.odometerService.updateOdometer(licensePlate, odometer, date)));
    }
}
