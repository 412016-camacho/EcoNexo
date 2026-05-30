package com.tfi.Econexo.mappers;

import com.tfi.Econexo.dto.auth.logistics.DriverRegistrationDTO;
import com.tfi.Econexo.dto.auth.logistics.DriverResponseDTO;
import com.tfi.Econexo.model.auth.UserSec;
import com.tfi.Econexo.model.location.Neighborhood;
import com.tfi.Econexo.model.logistics.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {VehicleMapper.class})
public interface DriverMapper {

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
    @Mapping(target = "location", expression = "java(com.tfi.Econexo.utils.GeometryUtils.createPoint(dto.longitude(), dto.latitude()))")
    Driver toEntity(DriverRegistrationDTO dto, UserSec user, Neighborhood neighborhood);

    @Mapping(target = "neighborhoodName", source = "driver.neighborhood.name")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "status", source = "status")
    DriverResponseDTO toResponseDTO(Driver driver);
}
