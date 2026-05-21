package com.tfi.Econexo.service.impl;

import com.tfi.Econexo.model.donation.Donor;
import com.tfi.Econexo.repository.donation.DonorRepository;
import com.tfi.Econexo.service.DonorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DonorServiceImpl implements DonorService {

    private final DonorRepository donorRepository;


    @Override
    public Boolean findByTaxId(String taxId) {
        return donorRepository.existsByTaxId(taxId);
    }

    @Override
    public Boolean findByEmail(String email) {
        return donorRepository.existsByUser_Email(email);
    }

    @Override
    public Donor save(Donor donor) {
        return donorRepository.save(donor);
    }
}
