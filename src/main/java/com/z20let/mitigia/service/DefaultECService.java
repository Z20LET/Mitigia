package com.z20let.mitigia.service;

import com.z20let.mitigia.model.DefaultEC;
import com.z20let.mitigia.repository.DefaultECRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class DefaultECService {
    private DefaultECRepository defaultECRepository;

    public Integer getDefaultEC() {
        Optional<DefaultEC> defaultEC = defaultECRepository.findById(1);
        return defaultEC.map(DefaultEC::getDefaultEC).orElse(null);
    }
}
