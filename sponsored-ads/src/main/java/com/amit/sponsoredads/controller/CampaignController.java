package com.amit.sponsoredads.controller;

import com.amit.sponsoredads.dto.campaign.CampaignDto;
import com.amit.sponsoredads.repository.CampaignRepository;
import com.amit.sponsoredads.service.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/campaigns")
@RestController
public class CampaignController {
    private CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    public ResponseEntity<CampaignDto> createCampaign(@RequestBody CampaignDto campaignDto) {
        CampaignDto resBody = campaignService.createCampaign(campaignDto);
        return new ResponseEntity<>(resBody, HttpStatus.CREATED);
    }
}