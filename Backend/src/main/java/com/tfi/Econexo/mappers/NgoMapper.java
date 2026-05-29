package com.tfi.Econexo.mappers;

import com.tfi.Econexo.dto.auth.ngo.NgoRegistrationDTO;
import com.tfi.Econexo.dto.auth.ngo.NgoResponseDTO;
import com.tfi.Econexo.model.auth.UserSec;
import com.tfi.Econexo.model.location.Neighborhood;
import com.tfi.Econexo.model.ngo.Ngo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NgoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "neighborhood", source = "neighborhood")
    @Mapping(target = "ngoName", source = "dto.ngoName")
    @Mapping(target = "taxId", source = "dto.taxId")
    @Mapping(target = "legalPersonalityNumber", source = "dto.legalPersonalityNumber")
    @Mapping(target = "responsibleName", source = "dto.responsibleName")
    @Mapping(target = "street", source = "dto.street")
    @Mapping(target = "streetNumber", source = "dto.streetNumber")
    @Mapping(target = "floor", source = "dto.floor")
    @Mapping(target = "apartment", source = "dto.apartment")
    @Mapping(target = "phoneNumber", source = "dto.phoneNumber")
    @Mapping(target = "ngoType", expression = "java(com.tfi.Econexo.model.ngo.NgoType.valueOf(dto.ngoType().toUpperCase()))")
    @Mapping(target = "location", expression = "java(com.tfi.Econexo.utils.GeometryUtils.createPoint(dto.longitude(), dto.latitude()))")
    Ngo toEntity(NgoRegistrationDTO dto, UserSec user, Neighborhood neighborhood);

    @Mapping(target = "email", source = "ngo.user.email")
    @Mapping(target = "neighborhoodId", source = "ngo.neighborhood.id")
    NgoResponseDTO toResponseDTO(Ngo ngo);

}
