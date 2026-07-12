package com.tfi.Econexo.repository.donation;

import com.tfi.Econexo.model.donation.ReceptionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceptionRecordRepository extends JpaRepository<ReceptionRecord, Long> {
    Optional<ReceptionRecord> findByDonationId(Long donationId);
}
