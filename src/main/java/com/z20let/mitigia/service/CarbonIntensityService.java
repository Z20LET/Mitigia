package com.z20let.mitigia.service;

import com.z20let.mitigia.repository.CarbonIntensityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CarbonIntensityService {
    private CarbonIntensityRepository carbonIntensityRepository;

    public Integer getCarbonIntensityByYear(Integer year) {
        return carbonIntensityRepository.findCarbonIntensityBy(year);
    }
}
