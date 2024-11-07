package com.z20let.mitigia.service;

import com.z20let.mitigia.model.CarbonIntensity;
import com.z20let.mitigia.repository.CarbonIntensityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
@AllArgsConstructor
public class CarbonIntensityService {

    private final CarbonIntensityRepository carbonIntensityRepository;

    public void saveCarbonIntensities(MultipartFile file) {
        if(ExcelToDatabaseService.validateExcelFile(file)) {
            try {
                List<CarbonIntensity> carbonIntensities = ExcelToDatabaseService.getCarbonIntensityData(file.getInputStream());
                carbonIntensityRepository.saveAll(carbonIntensities);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
