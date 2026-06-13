package com.tfi.Econexo.mappers;

import com.tfi.Econexo.dto.auth.logistics.VehicleRegistrationDTO;
import com.tfi.Econexo.dto.auth.logistics.VehicleResponseDTO;
import com.tfi.Econexo.model.logistics.Vehicle;
import com.tfi.Econexo.model.logistics.VehicleType;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-13T07:12:05-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class VehicleMapperImpl implements VehicleMapper {

    @Override
    public Vehicle toEntity(VehicleRegistrationDTO vehicleDTO) {
        if ( vehicleDTO == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setNumberPlate( vehicleDTO.numberPlate() );
        if ( vehicleDTO.hasRefrigeration() != null ) {
            vehicle.setHasRefrigeration( vehicleDTO.hasRefrigeration() );
        }
        vehicle.setVehicleType( vehicleDTO.vehicleType() );
        vehicle.setCapacityKg( vehicleDTO.capacityKg() );

        return vehicle;
    }

    @Override
    public VehicleResponseDTO toResponseDTO(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        Long id = null;
        VehicleType vehicleType = null;
        Boolean hasRefrigeration = null;
        int capacityKg = 0;
        String numberPlate = null;
        LocalDate driversLicenseExpiration = null;

        id = vehicle.getId();
        vehicleType = vehicle.getVehicleType();
        hasRefrigeration = vehicle.isHasRefrigeration();
        capacityKg = vehicle.getCapacityKg();
        numberPlate = vehicle.getNumberPlate();
        driversLicenseExpiration = vehicle.getDriversLicenseExpiration();

        VehicleResponseDTO vehicleResponseDTO = new VehicleResponseDTO( id, vehicleType, hasRefrigeration, capacityKg, numberPlate, driversLicenseExpiration );

        return vehicleResponseDTO;
    }
}
