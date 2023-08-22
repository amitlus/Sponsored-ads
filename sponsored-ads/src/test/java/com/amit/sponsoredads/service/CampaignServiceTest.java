package com.amit.sponsoredads.service;

import com.amit.sponsoredads.dto.campaign.CampaignDto;
import com.amit.sponsoredads.dto.campaign.CampaignMapper;
import com.amit.sponsoredads.model.Campaign;
import com.amit.sponsoredads.model.Product;
import com.amit.sponsoredads.repository.CampaignRepository;
import com.amit.sponsoredads.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CampaignServiceTest {

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private ProductRepository productRepository;
    @Mock
    private CampaignMapper campaignMapper;
    @Autowired
    @InjectMocks
    private CampaignService campaignService;
    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize annotated mocks
        campaignRepository.deleteAll();
        productRepository.deleteAll();
        product = new Product(4, "Test Product", "Test Category", 10.0, new ArrayList<>());
        productRepository.save(product);
    }

    @AfterEach
    public void tearDown() {
        campaignRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    void testCreateCampaign() {
        Campaign campaign = new Campaign(1L, "Test Campaign", LocalDateTime.now(ZoneOffset.UTC), List.of(product), 50.0);
        CampaignDto expectedCampaignDto = new CampaignDto("TestCampaign", campaign.getStartDate(), List.of(4), 50.0);

        when(campaignMapper.campaignDtoToCampaign(expectedCampaignDto)).thenReturn(campaign);
        CampaignDto resultCampaignDto = campaignService.createCampaign(expectedCampaignDto);

        assertEquals(expectedCampaignDto, resultCampaignDto);
    }
}