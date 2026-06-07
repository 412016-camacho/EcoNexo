package com.tfi.Econexo.mappers;

import com.tfi.Econexo.dto.donation.item.DonationItemRequestDTO;
import com.tfi.Econexo.dto.donation.item.DonationItemResponseDTO;
import com.tfi.Econexo.dto.donation.DonationResponseDTO;
import com.tfi.Econexo.model.donation.Donation;
import com.tfi.Econexo.model.donation.DonationItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DonationMapper {

    @Mapping(target = "businessName", source = "donor.tradeName")
    @Mapping(target = "status", expression = "java(donation.getStatus().name())")
    @Mapping(target = "items", source = "donationItems")
    @Mapping(target = "createdAt", source = "createdDate")
    DonationResponseDTO toResponseDTO(Donation donation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "donation", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "unitOfMeasure", ignore = true)
    DonationItem toItemEntity(DonationItemRequestDTO itemRequestDTO);

    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "category", source = "product.category.description")
    @Mapping(target = "productType", source = "product.productType.description")
    @Mapping(source = "unitOfMeasure.description", target = "unitOfMeasure")
    DonationItemResponseDTO toItemResponseDTO(DonationItem item);
}
