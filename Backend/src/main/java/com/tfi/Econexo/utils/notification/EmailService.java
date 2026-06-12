package com.tfi.Econexo.utils.notification;

public interface EmailService {

    void sendApprovalEmail(String toEmail, String recipientEmail, String role);
}
