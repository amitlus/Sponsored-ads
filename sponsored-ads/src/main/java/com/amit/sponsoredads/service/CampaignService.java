package com.amit.sponsoredads.service;

import com.amit.sponsoredads.dto.campaign.CampaignDto;
import com.amit.sponsoredads.dto.campaign.CampaignMapper;
import com.amit.sponsoredads.model.Campaign;
import com.amit.sponsoredads.repository.CampaignRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Data
@Service
public class CampaignService {
    private CampaignRepository campaignRepository;
    private CampaignMapper campaignMapper;
    private AdService adService;

    public CampaignService(CampaignRepository campaignRepository, CampaignMapper campaignMapper, AdService adService) {
        this.campaignMapper = campaignMapper;
        this.campaignRepository = campaignRepository;
        this.adService = adService;
    }

    public CampaignDto createCampaign(CampaignDto campaignDto) {
        Campaign createdCampaign = campaignRepository.save(campaignMapper.campaignDtoToCampaign(campaignDto));
        // Return the converted CampaignDto after saving
        return campaignMapper.campaignToCampaignDto(createdCampaign);
    }

    public Campaign getActiveMaxBidCampaignByCategory(String category) {
        Date tenDaysAgo = new Date(System.currentTimeMillis() - 10 * 24 * 60 * 60 * 1000);
        List<Campaign> activeCampaigns = campaignRepository.findCampaignsByStartDateGreaterThanAndProductsCategory(tenDaysAgo, category);

        if (!activeCampaigns.isEmpty()) {
            Campaign campaign = findCampaignWithMaxBid(activeCampaigns);
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

