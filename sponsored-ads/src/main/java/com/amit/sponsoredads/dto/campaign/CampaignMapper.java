package com.amit.sponsoredads.dto.campaign;

import com.amit.sponsoredads.dto.product.ProductMapper;
import com.amit.sponsoredads.model.Campaign;
import com.amit.sponsoredads.model.Product;
import com.amit.sponsoredads.service.AdService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {AdService.class, ProductMapper.class})
public interface CampaignMapper {
    @Mapping(source = "products", target = "productIds")
    CampaignDto campaignToCampaignDto(Campaign campaign);

    @Mapping(source = "productIds", target = "products")
    @InheritInverseConfiguration
    Campaign campaignDtoToCampaign(CampaignDto campaignDto);

    List<Product> mapProductIdsToProducts(List<Integer> productIds);

    List<Integer> mapProductsToProductIds(List<Product> products);

}

