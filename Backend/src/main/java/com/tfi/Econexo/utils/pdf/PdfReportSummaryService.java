package com.tfi.Econexo.utils.pdf;

import com.tfi.Econexo.model.donation.ReceptionRecord;

import java.time.LocalDate;
import java.util.List;

public interface PdfReportSummaryService {
    byte[] generateSummaryReport(Long donorId, LocalDate start, LocalDate end);
}
