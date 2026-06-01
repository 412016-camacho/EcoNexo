package com.tfi.Econexo.mappers;

import com.tfi.Econexo.dto.auth.donor.DonorRegistrationDTO;
import com.tfi.Econexo.dto.auth.donor.DonorResponseDTO;
import com.tfi.Econexo.model.auth.UserSec;
import com.tfi.Econexo.model.donation.donor.Donor;
import com.tfi.Econexo.model.location.Neighborhood;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DonorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "neighborhood", source = "neighborhood")
    @Mapping(target = "donorType", source = "dto.donorType")
    @Mapping(target = "location", expression = "java(com.tfi.Econexo.utils.GeometryUtils.createPoint(dto.longitude(), dto.latitude()))")
    Donor toEntity(DonorRegistrationDTO dto, UserSec user, Neighborhood neighborhood);

    @Mapping(target = "neighborhoodId", source = "donor.neighborhood.id")
    @Mapping(target = "email", source = "donor.user.email")
    @Mapping(target = "status", source = "status")
    DonorResponseDTO toResponseDTO(Donor donor);
}
