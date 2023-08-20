package com.amit.sponsoredads.controller;

import com.amit.sponsoredads.BaseTest;
import com.amit.sponsoredads.dto.campaign.CampaignDto;
import com.amit.sponsoredads.service.CampaignService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AdControllerIT extends BaseTest {
    @Autowired
    private CampaignService campaignService;

    @BeforeEach
    @Override
    protected void performAdditionalSetup() {
        CampaignDto campaign1 = new CampaignDto("Test Campaign 1", LocalDateTime.now(ZoneOffset.UTC), List.of(4, 5), 40.0);
        CampaignDto campaign2 = new CampaignDto("Test Campaign 2", LocalDateTime.now(ZoneOffset.UTC), List.of(4), 50.0);
        CampaignDto campaign3 = new CampaignDto("Test Campaign 3", LocalDateTime.now(ZoneOffset.UTC).minusDays(11), List.of(5), 100.0);
        campaignService.createCampaign(campaign1);
        campaignService.createCampaign(campaign2);
        campaignService.createCampaign(campaign3);
    }

    @Test
    void testServeAdWithValidCategory() throws Exception {
        // Perform the GET request to my API endpoint
        ResultActions result = mockMvc.perform(get("/ads")
                .param("category", "Test Category 1")
                .contentType(MediaType.APPLICATION_JSON));

        // Assert the HTTP response
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("title").value("Test Product 1"))
                .andExpect(jsonPath("price").value(10.0));
    }

    @Test
    void testServeAdWithInvalidCategory() throws Exception {
        ResultActions result = mockMvc.perform(get("/ads")
                .param("category", "Test Category N/A")
                .contentType(MediaType.APPLICATION_JSON));

        // Assert the HTTP response
        result.andExpect(status().isNotFound());
    }
}