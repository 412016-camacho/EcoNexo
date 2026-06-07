package com.tfi.Econexo.service.donation;

import com.tfi.Econexo.dto.donation.DonationRequestDTO;
import com.tfi.Econexo.dto.donation.DonationResponseDTO;
import com.tfi.Econexo.dto.donation.DonationSummaryResponseDTO;

import java.util.List;

public interface DonationService {

    DonationResponseDTO donate(DonationRequestDTO donationRequestDTO);
    List<DonationSummaryResponseDTO> getAvailableDonationsSummary();
}
