package com.amit.sponsoredads.controller;

import com.amit.sponsoredads.dto.product.ProductDto;
import com.amit.sponsoredads.model.Campaign;
import com.amit.sponsoredads.service.AdService;
import com.amit.sponsoredads.service.CampaignService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdControllerTest {

    private AdController adController;
    private CampaignService campaignService;
    private AdService adService;

    @BeforeEach
    public void setUp() {
        campaignService = mock(CampaignService.class);
        adService = mock(AdService.class);
        adController = new AdController(campaignService, adService);
    }

    @Test
    void testServeAdWithValidCategory() {
        // Arrange
        String category = "Category1";
        Campaign campaign = new Campaign();
        ProductDto productDto = new ProductDto();

        when(campaignService.getActiveMaxBidCampaignByCategory(category)).thenReturn(campaign);
        when(adService.findProductByCategoryAndCampaign(category, campaign)).thenReturn(productDto);

        // Act
        ResponseEntity<ProductDto> response = adController.serveAd(category);

        // Assert
        verify(campaignService).getActiveMaxBidCampaignByCategory(category);
        verify(adService).findProductByCategoryAndCampaign(category, campaign);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDto, response.getBody());
    }

    @Test
    void testServeAdWithInvalidCategory() {
        // Arrange
        String category = "invalidCategory";

        when(campaignService.getActiveMaxBidCampaignByCategory(category)).thenReturn(null);

        // Act
        ResponseEntity<ProductDto> response = adController.serveAd(category);

        // Assert
        verify(campaignService).getActiveMaxBidCampaignByCategory(category);
        verifyNoInteractions(adService); // adService should not be invoked
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}