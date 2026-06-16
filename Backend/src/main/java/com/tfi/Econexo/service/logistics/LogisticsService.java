package com.tfi.Econexo.service.logistics;

import com.tfi.Econexo.dto.donation.DonationResponseDTO;

import java.util.List;

public interface LogisticsService {
    List<DonationResponseDTO> getAvailableTripsNearby(String driverEmail, Double latitude, Double longitude);
}
