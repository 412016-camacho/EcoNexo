package com.tfi.Econexo.service.payment;

import com.tfi.Econexo.dto.payment.PaymentRequestDTO;

import java.util.Optional;

public interface MoneyDonationService {

    Long createMoneyDonation(PaymentRequestDTO dto, Optional<String> donorEmail);
}
