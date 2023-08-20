package com.amit.sponsoredads.service;

import com.amit.sponsoredads.dto.campaign.CampaignDto;
import com.amit.sponsoredads.dto.campaign.CampaignMapper;
import com.amit.sponsoredads.model.Campaign;
import com.amit.sponsoredads.repository.CampaignRepository;
import com.amit.sponsoredads.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CampaignService {
    private CampaignRepository campaignRepository;
    private ProductRepository productRepository;
    private CampaignMapper campaignMapper;

    @Transactional
    public CampaignDto createCampaign(CampaignDto campaignDto) {
        log.info("Creating campaign: {}", campaignDto.getName());
        Campaign createdCampaign = campaignRepository.save(campaignMapper.campaignDtoToCampaign(campaignDto));
        createdCampaign.getProducts().forEach((product -> {
            product.getCampaigns().add(createdCampaign);
            productRepository.save(product);
        }));
        // Return the converted CampaignDto after saving
        return campaignMapper.campaignToCampaignDto(createdCampaign);
    }

    public Campaign getActiveMaxBidCampaignByCategory(String category) {
        Date tenDaysAgo = new Date(System.currentTimeMillis() - 10 * 24 * 60 * 60 * 1000);
        List<Campaign> activeCampaigns = campaignRepository.findCampaignsByStartDateGreaterThanAndProductsCategory(tenDaysAgo, category);

        if (!activeCampaigns.isEmpty()) {
            return findCampaignWithMaxBid(activeCampaigns);
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

