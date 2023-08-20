package com.amit.sponsoredads.controller;

import com.amit.sponsoredads.dto.campaign.CampaignDto;
import com.amit.sponsoredads.service.CampaignService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/campaigns")
@RestController
public class CampaignController {
    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    public ResponseEntity<CampaignDto> createCampaign(@Valid @RequestBody CampaignDto campaignDto) {
        log.info("Received request to create a new Campaign: {}", campaignDto);
        CampaignDto resBody = campaignService.createCampaign(campaignDto);
        return new ResponseEntity<>(resBody, HttpStatus.CREATED);
    }
}