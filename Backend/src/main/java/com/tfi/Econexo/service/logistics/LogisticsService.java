package com.tfi.Econexo.service.logistics;

import com.tfi.Econexo.dto.donation.DonationResponseDTO;
import com.tfi.Econexo.model.donation.Donation;

import java.util.List;
import java.util.Optional;

public interface LogisticsService {
    List<DonationResponseDTO> getAvailableTripsNearby(String driverEmail, Double latitude, Double longitude);
    void acceptTrip(Long donationId, String driverEmail, Long vehicleId);
}
