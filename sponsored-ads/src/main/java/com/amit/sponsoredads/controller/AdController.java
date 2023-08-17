package com.amit.sponsoredads.controller;

import com.amit.sponsoredads.dto.product.ProductDto;
import com.amit.sponsoredads.model.Campaign;
import com.amit.sponsoredads.repository.CampaignRepository;
import com.amit.sponsoredads.service.CampaignService;
import com.amit.sponsoredads.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdController {
    private CampaignService campaignService;
    private ProductService productService;

    public AdController(CampaignService campaignService, ProductService productService) {
        this.campaignService = campaignService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ProductDto> serveAd(@RequestParam String category) {
        Campaign campaign = campaignService.getMaxBidCampaignByCategory(category);

        if (campaign == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductDto productDto = productService.getProductByCategoryAndCampaign(category, campaign);

        if (productDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }


}

