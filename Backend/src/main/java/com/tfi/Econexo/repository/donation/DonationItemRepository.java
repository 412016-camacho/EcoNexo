package com.tfi.Econexo.repository.donation;

import com.tfi.Econexo.model.donation.DonationItem;
import com.tfi.Econexo.model.enums.DonationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationItemRepository extends JpaRepository<DonationItem, Long> {

    List<DonationItem> findByDonation_StatusOrderByExpirationDateAsc(DonationStatus status);
}
