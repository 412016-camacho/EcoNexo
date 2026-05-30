package com.tfi.Econexo.model.logistics;

import com.tfi.Econexo.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle extends BaseEntity {

    @Column(name = "number_plate", unique = true)
    private String numberPlate;

    @Column(name = "has_refrigeration")
    private boolean hasRefrigeration;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    private VehicleType vehicleType;

    @Column(name = "capacity_kg" )
    private int capacityKg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @Column(name = "drivers_license_front_url")
    private String driversLicenseFrontUrl;

    @Column(name = "drivers_license_back_url")
    private String driversLicenseBackUrl;

    @Column(name = "drivers_license_expiration")
    private LocalDate driversLicenseExpiration;
}
