package com.tfi.Econexo.model.donation;

import com.tfi.Econexo.model.base.BaseEntity;
import com.tfi.Econexo.model.donation.donor.Donor;
import com.tfi.Econexo.model.enums.DonationStatus;
import com.tfi.Econexo.model.logistics.Driver;
import com.tfi.Econexo.model.ngo.Ngo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "donations")
@Builder
public class Donation extends BaseEntity {

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DonationStatus status = DonationStatus.AVAILABLE;

    @Column(name = "pickup_start_time", nullable = false)
    private LocalDateTime pickupStartTime;

    @Column(name = "pickup_end_time", nullable = false)
    private LocalDateTime pickupEndTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ngo_id")
    private Ngo ngo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToMany(mappedBy = "donation", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<DonationItem> donationItems = new ArrayList<>();

}
