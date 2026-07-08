package com.tfi.Econexo.service.payment;

import java.math.BigDecimal;

public interface PaymentService {

    String createPreference(BigDecimal amount, String description);
}
