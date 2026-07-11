package com.tfi.Econexo.repository.donation;

import com.tfi.Econexo.model.donation.MoneyDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyDonationRepository extends JpaRepository<MoneyDonation, Long> {
}
