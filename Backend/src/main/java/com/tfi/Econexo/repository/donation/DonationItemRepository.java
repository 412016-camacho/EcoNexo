package com.tfi.Econexo.repository.donation;

import com.tfi.Econexo.model.donation.DonationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationItemRepository extends JpaRepository<DonationItem, Long> {
}
