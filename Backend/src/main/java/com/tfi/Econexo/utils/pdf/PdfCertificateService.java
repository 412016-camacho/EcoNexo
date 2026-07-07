package com.tfi.Econexo.utils.pdf;

import com.tfi.Econexo.model.donation.ReceptionRecord;

public interface PdfCertificateService {
    byte[] generateCertificate(ReceptionRecord record);
}
