package com.amit.sponsoredads.controller;

import com.amit.sponsoredads.dto.product.ProductDto;
import com.amit.sponsoredads.model.Campaign;
import com.amit.sponsoredads.service.CampaignService;
import com.amit.sponsoredads.service.AdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ads")
public class AdController {
    private CampaignService campaignService;
    private AdService adService;

    public AdController(CampaignService campaignService, AdService adService) {
        this.campaignService = campaignService;
        this.adService = adService;
    }

    @GetMapping
    public ResponseEntity<ProductDto> serveAd(@RequestParam String category) {
        Campaign campaign = campaignService.getActiveMaxBidCampaignByCategory(category);
        if (campaign == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductDto productDto = adService.getProductByCategoryAndCampaign(category, campaign);
        if (productDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

}

