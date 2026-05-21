package com.tfi.Econexo.service;

import com.tfi.Econexo.model.donation.Donor;

public interface DonorService {
    Boolean findByTaxId(String taxId);
    Boolean findByEmail(String email);
    Donor save(Donor donor);
}
