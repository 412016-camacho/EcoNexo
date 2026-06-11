package com.tfi.Econexo.mappers;

import com.tfi.Econexo.dto.donation.DonationItemSummaryDTO;
import com.tfi.Econexo.dto.donation.DonationResponseDTO;
import com.tfi.Econexo.dto.donation.DonationSummaryResponseDTO;
import com.tfi.Econexo.dto.donation.item.DonationItemRequestDTO;
import com.tfi.Econexo.dto.donation.item.DonationItemResponseDTO;
import com.tfi.Econexo.model.donation.Donation;
import com.tfi.Econexo.model.donation.DonationItem;
import com.tfi.Econexo.model.donation.catalog.Category;
import com.tfi.Econexo.model.donation.catalog.Product;
import com.tfi.Econexo.model.donation.catalog.ProductType;
import com.tfi.Econexo.model.donation.catalog.UnitOfMeasure;
import com.tfi.Econexo.model.donation.donor.Donor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-10T07:57:15-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class DonationMapperImpl implements DonationMapper {

    @Override
    public DonationResponseDTO toResponseDTO(Donation donation) {
        if ( donation == null ) {
            return null;
        }

        String businessName = null;
        List<DonationItemResponseDTO> items = null;
        LocalDateTime createdAt = null;
        Long id = null;
        LocalDateTime pickupStartTime = null;
        LocalDateTime pickupEndTime = null;

        businessName = donationDonorTradeName( donation );
        items = donationItemListToDonationItemResponseDTOList( donation.getDonationItems() );
        createdAt = donation.getCreatedDate();
        id = donation.getId();
        pickupStartTime = donation.getPickupStartTime();
        pickupEndTime = donation.getPickupEndTime();

        String status = donation.getStatus().name();

        DonationResponseDTO donationResponseDTO = new DonationResponseDTO( id, status, pickupStartTime, pickupEndTime, createdAt, businessName, items );

        return donationResponseDTO;
    }

    @Override
    public DonationItem toItemEntity(DonationItemRequestDTO itemRequestDTO) {
        if ( itemRequestDTO == null ) {
            return null;
        }

        DonationItem donationItem = new DonationItem();

        donationItem.setDescription( itemRequestDTO.description() );
        donationItem.setQuantity( itemRequestDTO.quantity() );
        donationItem.setBatchNumber( itemRequestDTO.batchNumber() );
        donationItem.setProductionDate( itemRequestDTO.productionDate() );
        donationItem.setExpirationDate( itemRequestDTO.expirationDate() );
        donationItem.setDeliveryTemperature( itemRequestDTO.deliveryTemperature() );
        donationItem.setAllergenWarning( itemRequestDTO.allergenWarning() );
        donationItem.setObservations( itemRequestDTO.observations() );

        return donationItem;
    }

    @Override
    public DonationItemResponseDTO toItemResponseDTO(DonationItem item) {
        if ( item == null ) {
            return null;
        }

        String productName = null;
        String category = null;
        String productType = null;
        String unitOfMeasure = null;
        Long id = null;
        Double quantity = null;
        String batchNumber = null;
        LocalDateTime productionDate = null;
        LocalDateTime expirationDate = null;
        String deliveryTemperature = null;
        String allergenWarning = null;
        String observations = null;
        String description = null;

        productName = itemProductName( item );
        category = itemProductCategoryDescription( item );
        productType = itemProductProductTypeDescription( item );
        unitOfMeasure = itemUnitOfMeasureDescription( item );
        id = item.getId();
        quantity = item.getQuantity();
        batchNumber = item.getBatchNumber();
        productionDate = item.getProductionDate();
        expirationDate = item.getExpirationDate();
        deliveryTemperature = item.getDeliveryTemperature();
        allergenWarning = item.getAllergenWarning();
        observations = item.getObservations();
        description = item.getDescription();

        DonationItemResponseDTO donationItemResponseDTO = new DonationItemResponseDTO( id, productName, category, productType, quantity, unitOfMeasure, batchNumber, productionDate, expirationDate, deliveryTemperature, allergenWarning, observations, description );

        return donationItemResponseDTO;
    }

    @Override
    public DonationSummaryResponseDTO toSummaryResponseDTO(Donation donation) {
        if ( donation == null ) {
            return null;
        }

        String businessName = null;
        List<DonationItemSummaryDTO> items = null;
        Long id = null;

        businessName = donationDonorTradeName( donation );
        items = donationItemListToDonationItemSummaryDTOList( donation.getDonationItems() );
        id = donation.getId();

        LocalDateTime expirationDate = donation.getMinExpirationDate();
        boolean requiresRefrigeration = donation.isAnyItemRefrigerated();

        DonationSummaryResponseDTO donationSummaryResponseDTO = new DonationSummaryResponseDTO( id, businessName, expirationDate, requiresRefrigeration, items );

        return donationSummaryResponseDTO;
    }

    @Override
    public DonationItemSummaryDTO toItemSummaryDTO(DonationItem item) {
        if ( item == null ) {
            return null;
        }

        String productName = null;
        String unitOfMeasure = null;
        Integer quantity = null;
        String description = null;
        String allergenWarning = null;

        productName = itemProductName( item );
        unitOfMeasure = itemUnitOfMeasureDescription( item );
        if ( item.getQuantity() != null ) {
            quantity = item.getQuantity().intValue();
        }
        description = item.getDescription();
        allergenWarning = item.getAllergenWarning();

        DonationItemSummaryDTO donationItemSummaryDTO = new DonationItemSummaryDTO( productName, quantity, unitOfMeasure, description, allergenWarning );

        return donationItemSummaryDTO;
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

    protected List<DonationItemResponseDTO> donationItemListToDonationItemResponseDTOList(List<DonationItem> list) {
        if ( list == null ) {
            return null;
        }

        List<DonationItemResponseDTO> list1 = new ArrayList<DonationItemResponseDTO>( list.size() );
        for ( DonationItem donationItem : list ) {
            list1.add( toItemResponseDTO( donationItem ) );
        }

        return list1;
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

    private String itemUnitOfMeasureDescription(DonationItem donationItem) {
        if ( donationItem == null ) {
            return null;
        }
        UnitOfMeasure unitOfMeasure = donationItem.getUnitOfMeasure();
        if ( unitOfMeasure == null ) {
            return null;
        }
        String description = unitOfMeasure.getDescription();
        if ( description == null ) {
            return null;
        }
        return description;
    }

    protected List<DonationItemSummaryDTO> donationItemListToDonationItemSummaryDTOList(List<DonationItem> list) {
        if ( list == null ) {
            return null;
        }

        List<DonationItemSummaryDTO> list1 = new ArrayList<DonationItemSummaryDTO>( list.size() );
        for ( DonationItem donationItem : list ) {
            list1.add( toItemSummaryDTO( donationItem ) );
        }

        return list1;
    }
}
