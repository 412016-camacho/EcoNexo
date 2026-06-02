package com.tfi.Econexo.service;

import com.tfi.Econexo.dto.donation.DonationRequestDTO;
import com.tfi.Econexo.dto.donation.DonationResponseDTO;

public interface DonationService {

    DonationResponseDTO donate(DonationRequestDTO donationRequestDTO);
}
