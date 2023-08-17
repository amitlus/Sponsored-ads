package com.amit.sponsoredads.dto.campaign;

import com.amit.sponsoredads.model.Campaign;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CampaignMapper {
    CampaignMapper INSTANCE = Mappers.getMapper(CampaignMapper.class);

    //    @Mapping(source = "numberOfSeats", target = "seatCount")
    CampaignDto campaignToCampaignDto(Campaign campaign);

    @InheritInverseConfiguration
    Campaign campaignDtoToCampaign(CampaignDto campaignDto);

}