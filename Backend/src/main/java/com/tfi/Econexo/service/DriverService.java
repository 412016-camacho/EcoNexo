package com.tfi.Econexo.service;

import com.tfi.Econexo.model.logistics.Driver;

import java.util.Optional;

public interface DriverService {

    Optional<Driver> findByTaxId(String taxId);
    Driver save(Driver driver);
}
