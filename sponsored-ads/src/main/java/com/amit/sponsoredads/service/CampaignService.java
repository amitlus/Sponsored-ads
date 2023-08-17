package com.amit.sponsoredads.service;

import com.amit.sponsoredads.dto.campaign.CampaignDto;
import com.amit.sponsoredads.dto.campaign.CampaignMapper;
import com.amit.sponsoredads.model.Campaign;
import com.amit.sponsoredads.repository.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {
    private CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public CampaignDto createCampaign(CampaignDto campaignDto) {
        Campaign createdCampaign = campaignRepository.save(CampaignMapper.INSTANCE.campaignDtoToCampaign(campaignDto));
        // Return the converted CampaignDto after saving
        return CampaignMapper.INSTANCE.campaignToCampaignDto(createdCampaign);
    }

    public Campaign getMaxBidCampaignByCategory(String category) {
        List<Campaign> campaigns = campaignRepository.findDistinctByProductsCategory(category);
        if (!campaigns.isEmpty()) {
            Campaign campaign = findCampaignWithMaxBid(campaigns);
            if (campaign != null) {
                return campaign;
            }
        }
        return null;

    }

    private Campaign findCampaignWithMaxBid(List<Campaign> campaigns) {
        Campaign campaignWithMaxBid = null;
        double maxBid = Double.MIN_VALUE;

        for (Campaign campaign : campaigns) {
            if (campaign.getBid() > maxBid) {
                maxBid = campaign.getBid();
                campaignWithMaxBid = campaign;
            }
        }

        return campaignWithMaxBid;
    }

}

