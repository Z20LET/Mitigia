package com.z20let.mitigia.service;

import com.z20let.mitigia.model.CarbonIntensity;
import com.z20let.mitigia.repository.CarbonIntensityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CarbonIntensityService {
    private CarbonIntensityRepository carbonIntensityRepository;

    public Integer getCarbonIntensity(Integer id) {
        Optional<CarbonIntensity> carbonIntensity = carbonIntensityRepository.findById(id);
        return carbonIntensity.map(CarbonIntensity::getCarbonIntensity).orElse(null);
    }
}
