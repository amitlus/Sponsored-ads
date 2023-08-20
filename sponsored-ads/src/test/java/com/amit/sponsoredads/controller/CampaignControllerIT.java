package com.amit.sponsoredads.controller;

import com.amit.sponsoredads.BaseTest;
import com.amit.sponsoredads.dto.campaign.CampaignDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CampaignControllerIT extends BaseTest {
    private CampaignDto campaign1;
    private CampaignDto campaign2;

    @BeforeEach
    @Override
    protected void performAdditionalSetup() {
        campaign1 = new CampaignDto("Test Campaign 1", LocalDateTime.now(ZoneOffset.UTC).plusDays(1), List.of(4, 5), 40.0);
        campaign2 = new CampaignDto("Test Campaign 2", LocalDateTime.now(ZoneOffset.UTC).minusDays(1), List.of(4, 5), 40.0);
    }

    @Test
    void createCampaignWithValidCampaignDto() throws Exception {
        String requestBodyForCampaignDto = objectMapper.writeValueAsString(campaign1);
        // Perform the POST request to my API endpoint
        ResultActions result = mockMvc.perform(post("/campaigns")
                .content(requestBodyForCampaignDto)
                .contentType(MediaType.APPLICATION_JSON));

        // Assert the HTTP response
        result.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value("Test Campaign 1"));
    }

    @Test
    void createCampaignWithInvalidCampaignDto() throws Exception {
        String requestBodyForCampaignDto = objectMapper.writeValueAsString(campaign2);
        // Perform the POST request to my API endpoint
        ResultActions result = mockMvc.perform(post("/campaigns")
                .content(requestBodyForCampaignDto)
                .contentType(MediaType.APPLICATION_JSON));

        // Assert the HTTP response
        result.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}