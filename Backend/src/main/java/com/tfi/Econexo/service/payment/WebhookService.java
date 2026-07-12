package com.tfi.Econexo.service.payment;

public interface WebhookService {

    void processWebhook(String merchantOrderId);
}
