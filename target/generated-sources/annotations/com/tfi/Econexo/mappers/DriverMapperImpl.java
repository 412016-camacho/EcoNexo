package com.tfi.Econexo.mappers;

import com.tfi.Econexo.dto.auth.logistics.DriverRegistrationDTO;
import com.tfi.Econexo.dto.auth.logistics.DriverResponseDTO;
import com.tfi.Econexo.dto.auth.logistics.VehicleResponseDTO;
import com.tfi.Econexo.model.auth.UserSec;
import com.tfi.Econexo.model.location.Neighborhood;
import com.tfi.Econexo.model.logistics.Driver;
import com.tfi.Econexo.model.logistics.Vehicle;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-10T07:57:16-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class DriverMapperImpl implements DriverMapper {

    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public Driver toEntity(DriverRegistrationDTO dto, UserSec user, Neighborhood neighborhood) {
        if ( dto == null && user == null && neighborhood == null ) {
            return null;
        }

        Driver driver = new Driver();

        if ( dto != null ) {
            driver.setFirstName( dto.firstName() );
            driver.setLastName( dto.lastName() );
            driver.setBirthDate( dto.birthDate() );
            driver.setFoodHandlerCertificateUrl( dto.foodHandlerCertificateUrl() );
            driver.setFoodHandlerCertificateExpiration( dto.foodHandlerCertificateExpiration() );
            driver.setTaxId( dto.taxId() );
            driver.setPhoneNumber( dto.phoneNumber() );
            driver.setStreet( dto.street() );
            driver.setStreetNumber( dto.streetNumber() );
            driver.setFloor( dto.floor() );
            driver.setApartment( dto.apartment() );
        }
        driver.setUser( user );
        driver.setNeighborhood( neighborhood );
        driver.setLocation( com.tfi.Econexo.utils.GeometryUtils.createPoint(dto.longitude(), dto.latitude()) );

        return driver;
    }

    @Override
    public DriverResponseDTO toResponseDTO(Driver driver) {
        if ( driver == null ) {
            return null;
        }

        String neighborhoodName = null;
        String email = null;
        String status = null;
        Long id = null;
        String firstName = null;
        String lastName = null;
        String taxId = null;
        String phoneNumber = null;
        LocalDate birthDate = null;
        LocalDate foodHandlerCertificateExpiration = null;
        String street = null;
        String streetNumber = null;
        String floor = null;
        String apartment = null;
        List<VehicleResponseDTO> vehicles = null;

        neighborhoodName = driverNeighborhoodName( driver );
        email = driverUserEmail( driver );
        if ( driver.getStatus() != null ) {
            status = driver.getStatus().name();
        }
        id = driver.getId();
        firstName = driver.getFirstName();
        lastName = driver.getLastName();
        taxId = driver.getTaxId();
        phoneNumber = driver.getPhoneNumber();
        birthDate = driver.getBirthDate();
        foodHandlerCertificateExpiration = driver.getFoodHandlerCertificateExpiration();
        street = driver.getStreet();
        streetNumber = driver.getStreetNumber();
        floor = driver.getFloor();
        apartment = driver.getApartment();
        vehicles = vehicleListToVehicleResponseDTOList( driver.getVehicles() );

        DriverResponseDTO driverResponseDTO = new DriverResponseDTO( id, firstName, lastName, email, taxId, phoneNumber, birthDate, status, foodHandlerCertificateExpiration, street, streetNumber, floor, apartment, neighborhoodName, vehicles );

        return driverResponseDTO;
    }

    private String driverNeighborhoodName(Driver driver) {
        if ( driver == null ) {
            return null;
        }
        Neighborhood neighborhood = driver.getNeighborhood();
        if ( neighborhood == null ) {
            return null;
        }
        String name = neighborhood.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String driverUserEmail(Driver driver) {
        if ( driver == null ) {
            return null;
        }
        UserSec user = driver.getUser();
        if ( user == null ) {
            return null;
        }
        String email = user.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    protected List<VehicleResponseDTO> vehicleListToVehicleResponseDTOList(List<Vehicle> list) {
        if ( list == null ) {
            return null;
        }

        List<VehicleResponseDTO> list1 = new ArrayList<VehicleResponseDTO>( list.size() );
        for ( Vehicle vehicle : list ) {
            list1.add( vehicleMapper.toResponseDTO( vehicle ) );
        }

        return list1;
    }
}
