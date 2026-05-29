package com.tfi.Econexo.model.ngo;

import com.tfi.Econexo.model.RegistrationStatus;
import com.tfi.Econexo.model.auth.UserSec;
import com.tfi.Econexo.model.base.BaseEntity;
import com.tfi.Econexo.model.location.Neighborhood;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organizations")
public class Ngo extends BaseEntity {

    @Column(nullable = false)
    private String ngoName;

    @Column(nullable = false, unique = true, length = 11)
    private String taxId;

    @Column(nullable = false, unique = true)
    private String legalPersonalityNumber;

    @Column(nullable = false)
    private String responsibleName;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String streetNumber;

    @Column(nullable = true)
    private String floor;

    @Column(nullable = true)
    private String apartment;

    @Column(nullable = false)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "neighborhood_id", nullable = false)
    private Neighborhood neighborhood;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point location;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserSec user;

    @Enumerated(EnumType.STRING)
    private NgoType ngoType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationStatus status = RegistrationStatus.PENDING;
}
