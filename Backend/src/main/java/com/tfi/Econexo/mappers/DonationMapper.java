package com.tfi.Econexo.mappers;

import com.tfi.Econexo.dto.donation.DonationItemResponseDTO;
import com.tfi.Econexo.dto.donation.DonationResponseDTO;
import com.tfi.Econexo.model.donation.Donation;
import com.tfi.Econexo.model.donation.DonationItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DonationMapper {

    @Mapping(target = "businessName", source = "donor.tradeName")
    @Mapping(target = "status", expression = "java(donation.getStatus().name())")
    DonationResponseDTO toResponseDTO(Donation donation);

    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "unitOfMeasure", source = "product.unitOfMeasure.description")
    @Mapping(target = "category", source = "product.category.description")
    @Mapping(target = "productType", source = "product.productType.description")
    DonationItemResponseDTO toItemResponseDTO(DonationItem item);
}
