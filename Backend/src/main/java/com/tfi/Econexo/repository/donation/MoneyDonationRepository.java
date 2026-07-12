package com.tfi.Econexo.repository.donation;

import com.tfi.Econexo.dto.payment.MoneyDonationDTO;
import com.tfi.Econexo.model.donation.MoneyDonation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoneyDonationRepository extends JpaRepository<MoneyDonation, Long>, JpaSpecificationExecutor<MoneyDonation> {
    Page<MoneyDonationDTO> findByNgo_Id(Long ngoId, Pageable pageable);
}
