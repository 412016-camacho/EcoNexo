package com.tfi.Econexo.service;

import com.tfi.Econexo.model.ngo.Ngo;

import java.util.Optional;

public interface NgoService {

    Optional<Ngo> findByTaxId(String taxId);
    Optional<Ngo> findByLegalPersonalityNumber(String legalPersonalityNumber);
    Ngo save(Ngo ngo);
}
