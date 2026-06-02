package com.tfi.Econexo.mappers;

import com.tfi.Econexo.dto.donation.DonationItemResponseDTO;
import com.tfi.Econexo.dto.donation.DonationResponseDTO;
import com.tfi.Econexo.model.donation.Donation;
import com.tfi.Econexo.model.donation.DonationItem;
import com.tfi.Econexo.model.donation.catalog.Category;
import com.tfi.Econexo.model.donation.catalog.Product;
import com.tfi.Econexo.model.donation.catalog.ProductType;
import com.tfi.Econexo.model.donation.catalog.UnitOfMeasure;
import com.tfi.Econexo.model.donation.donor.Donor;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-02T11:42:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class DonationMapperImpl implements DonationMapper {

    private final DatatypeFactory datatypeFactory;

    public DonationMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public DonationResponseDTO toResponseDTO(Donation donation) {
        if ( donation == null ) {
            return null;
        }

        String businessName = null;
        Long id = null;
        LocalDateTime pickupStartTime = null;
        LocalDateTime pickupEndTime = null;

        businessName = donationDonorTradeName( donation );
        id = donation.getId();
        pickupStartTime = donation.getPickupStartTime();
        pickupEndTime = donation.getPickupEndTime();

        String status = donation.getStatus().name();
        LocalDateTime createdAt = null;
        List<DonationItemResponseDTO> items = null;

        DonationResponseDTO donationResponseDTO = new DonationResponseDTO( id, status, pickupStartTime, pickupEndTime, createdAt, businessName, items );

        return donationResponseDTO;
    }

    @Override
    public DonationItemResponseDTO toItemResponseDTO(DonationItem item) {
        if ( item == null ) {
            return null;
        }

        String productName = null;
        String unitOfMeasure = null;
        String category = null;
        String productType = null;
        Long id = null;
        Double quantity = null;
        String batchNumber = null;
        LocalDateTime productionDate = null;
        LocalDateTime expirationDate = null;
        String deliveryTemperature = null;
        String allergenWarning = null;
        String observations = null;

        productName = itemProductName( item );
        unitOfMeasure = itemProductUnitOfMeasureDescription( item );
        category = itemProductCategoryDescription( item );
        productType = itemProductProductTypeDescription( item );
        id = item.getId();
        quantity = item.getQuantity();
        batchNumber = item.getBatchNumber();
        productionDate = item.getProductionDate();
        expirationDate = xmlGregorianCalendarToLocalDateTime( localDateToXmlGregorianCalendar( item.getExpirationDate() ) );
        deliveryTemperature = item.getDeliveryTemperature();
        allergenWarning = item.getAllergenWarning();
        observations = item.getObservations();

        DonationItemResponseDTO donationItemResponseDTO = new DonationItemResponseDTO( id, productName, category, productType, quantity, unitOfMeasure, batchNumber, productionDate, expirationDate, deliveryTemperature, allergenWarning, observations );

        return donationItemResponseDTO;
    }

    private XMLGregorianCalendar localDateToXmlGregorianCalendar( LocalDate localDate ) {
        if ( localDate == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendarDate(
            localDate.getYear(),
            localDate.getMonthValue(),
            localDate.getDayOfMonth(),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private static LocalDateTime xmlGregorianCalendarToLocalDateTime( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        if ( xcal.getYear() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getMonth() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getDay() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getHour() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getMinute() != DatatypeConstants.FIELD_UNDEFINED
        ) {
            if ( xcal.getSecond() != DatatypeConstants.FIELD_UNDEFINED
                && xcal.getMillisecond() != DatatypeConstants.FIELD_UNDEFINED ) {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute(),
                    xcal.getSecond(),
                    Duration.ofMillis( xcal.getMillisecond() ).getNano()
                );
            }
            else if ( xcal.getSecond() != DatatypeConstants.FIELD_UNDEFINED ) {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute(),
                    xcal.getSecond()
                );
            }
            else {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute()
                );
            }
        }
        return null;
    }

    private String donationDonorTradeName(Donation donation) {
        if ( donation == null ) {
            return null;
        }
        Donor donor = donation.getDonor();
        if ( donor == null ) {
            return null;
        }
        String tradeName = donor.getTradeName();
        if ( tradeName == null ) {
            return null;
        }
        return tradeName;
    }

    private String itemProductName(DonationItem donationItem) {
        if ( donationItem == null ) {
            return null;
        }
        Product product = donationItem.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String itemProductUnitOfMeasureDescription(DonationItem donationItem) {
        if ( donationItem == null ) {
            return null;
        }
        Product product = donationItem.getProduct();
        if ( product == null ) {
            return null;
        }
        UnitOfMeasure unitOfMeasure = product.getUnitOfMeasure();
        if ( unitOfMeasure == null ) {
            return null;
        }
        String description = unitOfMeasure.getDescription();
        if ( description == null ) {
            return null;
        }
        return description;
    }

    private String itemProductCategoryDescription(DonationItem donationItem) {
        if ( donationItem == null ) {
            return null;
        }
        Product product = donationItem.getProduct();
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        String description = category.getDescription();
        if ( description == null ) {
            return null;
        }
        return description;
    }

    private String itemProductProductTypeDescription(DonationItem donationItem) {
        if ( donationItem == null ) {
            return null;
        }
        Product product = donationItem.getProduct();
        if ( product == null ) {
            return null;
        }
        ProductType productType = product.getProductType();
        if ( productType == null ) {
            return null;
        }
        String description = productType.getDescription();
        if ( description == null ) {
            return null;
        }
        return description;
    }
}
