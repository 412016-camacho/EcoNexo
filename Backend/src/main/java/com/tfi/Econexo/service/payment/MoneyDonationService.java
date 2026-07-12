package com.tfi.Econexo.service.payment;

import com.tfi.Econexo.dto.payment.MoneyDonationDTO;
import com.tfi.Econexo.dto.payment.PaymentRequestDTO;
import com.tfi.Econexo.model.enums.DonationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MoneyDonationService {

    Long createMoneyDonation(PaymentRequestDTO dto, Optional<String> donorEmail);
    Page<MoneyDonationDTO> getDonations(String ngoEmail, DonationStatus status, Pageable pageable);
}
