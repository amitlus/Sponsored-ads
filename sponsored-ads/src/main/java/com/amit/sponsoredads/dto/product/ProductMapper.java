package com.amit.sponsoredads.dto.product;

import com.amit.sponsoredads.dto.campaign.CampaignDto;
import com.amit.sponsoredads.model.Campaign;
import com.amit.sponsoredads.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

//    @Mapping(source = "numberOfSeats", target = "seatCount")
    ProductDto productToProductDto(Product product);
    @InheritInverseConfiguration
    Product productDtoToProduct(ProductDto productDto);
}
