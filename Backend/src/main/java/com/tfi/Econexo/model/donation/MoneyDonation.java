package com.tfi.Econexo.model.donation;

import com.tfi.Econexo.model.base.BaseEntity;
import com.tfi.Econexo.model.donation.donor.Donor;
import com.tfi.Econexo.model.enums.DonationStatus;
import com.tfi.Econexo.model.ngo.Ngo;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "money_donations")
public class MoneyDonation extends BaseEntity {

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "ngo_id")
    private Ngo ngo;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @Enumerated(EnumType.STRING)
    private DonationStatus status;
}
