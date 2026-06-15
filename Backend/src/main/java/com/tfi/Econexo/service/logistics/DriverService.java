package com.tfi.Econexo.service.logistics;

import com.tfi.Econexo.dto.auth.logistics.DriverResponseDTO;
import com.tfi.Econexo.dto.donation.DonationResponseDTO;
import com.tfi.Econexo.model.logistics.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    Optional<Driver> findByTaxId(String taxId);
    Driver save(Driver driver);
    DriverResponseDTO getProfileByEmail(String email);
    Optional<Driver> findEntityByEmail(String email);
}
