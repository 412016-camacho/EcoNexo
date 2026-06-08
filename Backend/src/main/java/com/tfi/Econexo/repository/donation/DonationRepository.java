package com.tfi.Econexo.repository.donation;

import com.tfi.Econexo.model.donation.Donation;
import com.tfi.Econexo.model.enums.DonationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByStatus(DonationStatus status);
}
