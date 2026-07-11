package com.tfi.Econexo.service.payment;

import com.tfi.Econexo.dto.payment.PaymentRequestDTO;

public interface PaymentService {

    String createPreference(PaymentRequestDTO dto);
}
