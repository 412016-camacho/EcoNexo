package com.tfi.Econexo.mappers;

import com.tfi.Econexo.dto.DonorRegistrationDTO;
import com.tfi.Econexo.dto.DonorResponseDTO;
import com.tfi.Econexo.model.auth.UserSec;
import com.tfi.Econexo.model.donation.Donor;
import com.tfi.Econexo.model.donation.DonorType;
import com.tfi.Econexo.model.location.Neighborhood;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-26T15:35:58-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class DonorMapperImpl implements DonorMapper {

    @Override
    public Donor toEntity(DonorRegistrationDTO dto, UserSec user, Neighborhood neighborhood) {
        if ( dto == null && user == null && neighborhood == null ) {
            return null;
        }

        Donor donor = new Donor();

        if ( dto != null ) {
            if ( dto.donorType() != null ) {
                donor.setDonorType( Enum.valueOf( DonorType.class, dto.donorType() ) );
            }
            donor.setTradeName( dto.tradeName() );
            donor.setLegalName( dto.legalName() );
            donor.setTaxId( dto.taxId() );
            donor.setPhoneNumber( dto.phoneNumber() );
            donor.setStreet( dto.street() );
            donor.setStreetNumber( dto.streetNumber() );
            donor.setFloor( dto.floor() );
            donor.setApartment( dto.apartment() );
        }
        donor.setUser( user );
        donor.setNeighborhood( neighborhood );
        donor.setLocation( com.tfi.Econexo.utils.GeometryUtils.createPoint(dto.longitude(), dto.latitude()) );

        return donor;
    }

    @Override
    public DonorResponseDTO toResponseDTO(Donor donor) {
        if ( donor == null ) {
            return null;
        }

        Long neighborhoodId = null;
        String email = null;
        Long id = null;
        String tradeName = null;
        String legalName = null;
        String taxId = null;
        String phoneNumber = null;
        String street = null;
        String streetNumber = null;
        String floor = null;
        String apartment = null;

        neighborhoodId = donorNeighborhoodId( donor );
        email = donorUserEmail( donor );
        id = donor.getId();
        tradeName = donor.getTradeName();
        legalName = donor.getLegalName();
        taxId = donor.getTaxId();
        phoneNumber = donor.getPhoneNumber();
        street = donor.getStreet();
        streetNumber = donor.getStreetNumber();
        floor = donor.getFloor();
        apartment = donor.getApartment();

        DonorResponseDTO donorResponseDTO = new DonorResponseDTO( id, email, tradeName, legalName, taxId, phoneNumber, street, streetNumber, floor, apartment, neighborhoodId );

        return donorResponseDTO;
    }

    private Long donorNeighborhoodId(Donor donor) {
        if ( donor == null ) {
            return null;
        }
        Neighborhood neighborhood = donor.getNeighborhood();
        if ( neighborhood == null ) {
            return null;
        }
        Long id = neighborhood.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String donorUserEmail(Donor donor) {
        if ( donor == null ) {
            return null;
        }
        UserSec user = donor.getUser();
        if ( user == null ) {
            return null;
        }
        String email = user.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }
}
