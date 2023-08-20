package com.amit.sponsoredads.service;

import com.amit.sponsoredads.BaseTest;
import com.amit.sponsoredads.dto.campaign.CampaignDto;
import com.amit.sponsoredads.dto.product.ProductDto;
import com.amit.sponsoredads.model.Campaign;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class AdServiceTest extends BaseTest {
    @Autowired
    private CampaignService campaignService;
    @Autowired
    private AdService adService;
    private CampaignDto campaign1;
    private CampaignDto campaign2;

    @BeforeEach
    @Override
    protected void performAdditionalSetup() {
        campaign1 = new CampaignDto("Test Campaign 1", LocalDateTime.now(ZoneOffset.UTC), List.of(4, 5), 40.0);
        campaign2 = new CampaignDto("Test Campaign 2", LocalDateTime.now(ZoneOffset.UTC), List.of(4, 5), 50.0);
    }

    @Test
    void findProductByCategoryAndCampaign() {
        campaignService.createCampaign(campaign1);
        campaignService.createCampaign(campaign2);
        Campaign campaign = campaignRepository.findCampaignByName("Test Campaign 2");
        ProductDto productDto = adService.findProductByCategoryAndCampaign("Test Category 1", campaign);
        assertEquals(product1.getTitle(), productDto.getTitle());
        assertEquals(product1.getCategory(), productDto.getCategory());
    }

    @Test
    void findProductByProductSerialNumber() {
        campaignService.createCampaign(campaign1);
        campaignService.createCampaign(campaign2);
        assertEquals(product1, productRepository.findProductByProductSerialNumber(4));
    }
}