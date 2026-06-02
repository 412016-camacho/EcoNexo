package com.tfi.Econexo.mappers;

import com.tfi.Econexo.dto.auth.ngo.NgoRegistrationDTO;
import com.tfi.Econexo.dto.auth.ngo.NgoResponseDTO;
import com.tfi.Econexo.model.auth.UserSec;
import com.tfi.Econexo.model.location.Neighborhood;
import com.tfi.Econexo.model.ngo.Ngo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-02T16:10:20-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class NgoMapperImpl implements NgoMapper {

    @Override
    public Ngo toEntity(NgoRegistrationDTO dto, UserSec user, Neighborhood neighborhood) {
        if ( dto == null && user == null && neighborhood == null ) {
            return null;
        }

        Ngo ngo = new Ngo();

        if ( dto != null ) {
            ngo.setNgoName( dto.ngoName() );
            ngo.setTaxId( dto.taxId() );
            ngo.setLegalPersonalityNumber( dto.legalPersonalityNumber() );
            ngo.setResponsibleName( dto.responsibleName() );
            ngo.setStreet( dto.street() );
            ngo.setStreetNumber( dto.streetNumber() );
            ngo.setFloor( dto.floor() );
            ngo.setApartment( dto.apartment() );
            ngo.setPhoneNumber( dto.phoneNumber() );
        }
        ngo.setUser( user );
        ngo.setNeighborhood( neighborhood );
        ngo.setNgoType( com.tfi.Econexo.model.ngo.NgoType.valueOf(dto.ngoType().toUpperCase()) );
        ngo.setLocation( com.tfi.Econexo.utils.GeometryUtils.createPoint(dto.longitude(), dto.latitude()) );

        return ngo;
    }

    @Override
    public NgoResponseDTO toResponseDTO(Ngo ngo) {
        if ( ngo == null ) {
            return null;
        }

        String email = null;
        Long neighborhoodId = null;
        String status = null;
        Long id = null;
        String ngoName = null;
        String legalPersonalityNumber = null;
        String taxId = null;
        String responsibleName = null;
        String phoneNumber = null;
        String street = null;
        String streetNumber = null;
        String floor = null;
        String apartment = null;

        email = ngoUserEmail( ngo );
        neighborhoodId = ngoNeighborhoodId( ngo );
        if ( ngo.getStatus() != null ) {
            status = ngo.getStatus().name();
        }
        id = ngo.getId();
        ngoName = ngo.getNgoName();
        legalPersonalityNumber = ngo.getLegalPersonalityNumber();
        taxId = ngo.getTaxId();
        responsibleName = ngo.getResponsibleName();
        phoneNumber = ngo.getPhoneNumber();
        street = ngo.getStreet();
        streetNumber = ngo.getStreetNumber();
        floor = ngo.getFloor();
        apartment = ngo.getApartment();

        NgoResponseDTO ngoResponseDTO = new NgoResponseDTO( id, email, ngoName, legalPersonalityNumber, taxId, responsibleName, phoneNumber, street, streetNumber, floor, apartment, neighborhoodId, status );

        return ngoResponseDTO;
    }

    private String ngoUserEmail(Ngo ngo) {
        if ( ngo == null ) {
            return null;
        }
        UserSec user = ngo.getUser();
        if ( user == null ) {
            return null;
        }
        String email = user.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private Long ngoNeighborhoodId(Ngo ngo) {
        if ( ngo == null ) {
            return null;
        }
        Neighborhood neighborhood = ngo.getNeighborhood();
        if ( neighborhood == null ) {
            return null;
        }
        Long id = neighborhood.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
