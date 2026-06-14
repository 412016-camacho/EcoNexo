package com.tfi.Econexo.service.impl;

import com.tfi.Econexo.dto.auth.ngo.NgoResponseDTO;
import com.tfi.Econexo.mappers.NgoMapper;
import com.tfi.Econexo.model.ngo.Ngo;
import com.tfi.Econexo.repository.ngo.NgoRepository;
import com.tfi.Econexo.service.NgoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NgoServiceImpl implements NgoService {

    private final NgoRepository ngoRepository;
    private final NgoMapper ngoMapper;

    @Override
    public Optional<Ngo> findByTaxId(String taxId) {
        return ngoRepository.findByTaxId(taxId);
    }

    @Override
    public Optional<Ngo> findByLegalPersonalityNumber(String legalPersonalityNumber) {
        return ngoRepository.findByLegalPersonalityNumber(legalPersonalityNumber);
    }

    @Override
    public Ngo save(Ngo ngo) {
        return ngoRepository.save(ngo);
    }

    @Override
    public boolean existsEmail(String email) {
        return ngoRepository.existsByUser_Email(email);
    }

    @Override
    public NgoResponseDTO getProfileByEmail(String email) {
        Ngo ngo = ngoRepository.findByUser_Email(email)
                .orElseThrow(() -> new EntityNotFoundException("Ngo not found"));
        return ngoMapper.toResponseDTO(ngo);
    }
}
