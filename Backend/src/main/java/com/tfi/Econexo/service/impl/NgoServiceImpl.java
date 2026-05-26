package com.tfi.Econexo.service.impl;

import com.tfi.Econexo.model.ngo.Ngo;
import com.tfi.Econexo.service.NeighborhoodService;
import com.tfi.Econexo.service.NgoService;
import com.tfi.Econexo.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NgoServiceImpl implements NgoService {

    private final NgoService ngoService;

    @Override
    public Optional<Ngo> findByTaxId(String taxId) {
        return ngoService.findByTaxId(taxId);
    }

    @Override
    public Optional<Ngo> findByLegalPersonalityNumber(String legalPersonalityNumber) {
        return ngoService.findByLegalPersonalityNumber(legalPersonalityNumber);
    }

    @Override
    public Ngo save(Ngo ngo) {
        return ngoService.save(ngo);
    }
}
