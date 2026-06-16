package com.tfi.Econexo.service.impl.logistics;

import com.tfi.Econexo.dto.auth.logistics.DriverResponseDTO;
import com.tfi.Econexo.mappers.DriverMapper;
import com.tfi.Econexo.model.logistics.Driver;
import com.tfi.Econexo.repository.logistics.DriverRepository;
import com.tfi.Econexo.service.logistics.DriverService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    public Optional<Driver> findByTaxId(String taxId) {
        return driverRepository.findByTaxId(taxId);
    }

    @Override
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public DriverResponseDTO getProfileByEmail(String email) {
        Driver driver = driverRepository.findByUser_Email((email))
                .orElseThrow(() -> new EntityNotFoundException("Driver not found"));

        return driverMapper.toResponseDTO(driver);
    }

    @Override
    public Optional<Driver> findEntityByEmail(String email) {
        return driverRepository.findByUser_Email(email);
    }
}
