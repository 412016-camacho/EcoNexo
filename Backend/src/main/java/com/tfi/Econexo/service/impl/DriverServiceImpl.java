package com.tfi.Econexo.service.impl;

import com.tfi.Econexo.model.logistics.Driver;
import com.tfi.Econexo.repository.logistics.DriverRepository;
import com.tfi.Econexo.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Override
    public Optional<Driver> findByTaxId(String taxId) {
        return driverRepository.findByTaxId(taxId);
    }

    @Override
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }
}
