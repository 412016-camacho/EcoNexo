package com.tfi.Econexo.dto.payment;

import com.tfi.Econexo.model.enums.DonationStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Money donation details")
public record MoneyDonationDTO(

        @Schema(description = "Amount of the donation")
        BigDecimal amount,

        @Schema(description = "Status of the donation")
        DonationStatus status,

        @Schema(description = "Id of the NGO")
        Long ngoId,

        @Schema(description = "Id of the donor")
        Long donorId
) {
}
