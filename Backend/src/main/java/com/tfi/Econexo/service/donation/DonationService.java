package com.tfi.Econexo.service.donation;

import com.tfi.Econexo.dto.donation.DonationRequestDTO;
import com.tfi.Econexo.dto.donation.DonationResponseDTO;
import com.tfi.Econexo.dto.donation.DonationSummaryResponseDTO;
import com.tfi.Econexo.model.donation.Donation;
import com.tfi.Econexo.model.enums.DonationStatus;
import org.locationtech.jts.geom.Point;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonationService {

    DonationResponseDTO donate(DonationRequestDTO donationRequestDTO);
    List<DonationSummaryResponseDTO> getAvailableDonationsSummary();
    void requestDonation(Long donationId, String ngoEmail);
    List<Donation> findAvailableTripsNearby(
            @Param("driverLocation") Point driverLocation,
            @Param("driverId") Long driverId,
            @Param("status") DonationStatus status
    );
}
