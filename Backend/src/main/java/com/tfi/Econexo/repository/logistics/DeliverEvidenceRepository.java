package com.tfi.Econexo.repository.logistics;

import com.tfi.Econexo.model.logistics.DeliveryEvidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliverEvidenceRepository extends JpaRepository<DeliveryEvidence, Long> {
    Optional<DeliveryEvidence> findByDonationId(Long donationId);
}
