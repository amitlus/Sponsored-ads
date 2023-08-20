package com.amit.sponsoredads;

import com.amit.sponsoredads.model.Product;
import com.amit.sponsoredads.repository.CampaignRepository;
import com.amit.sponsoredads.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class BaseTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    protected CampaignRepository campaignRepository;
    protected Product product1;
    protected Product product2;

    @BeforeEach
    public void setUp() {
        campaignRepository.deleteAll();
        productRepository.deleteAll();
        product1 = new Product(4, "Test Product 1", "Test Category 1", 10.0, new ArrayList<>());
        product2 = new Product(5, "Test Product 2", "Test Category 1", 20.0, new ArrayList<>());
        productRepository.save(product1);
        productRepository.save(product2);
        performAdditionalSetup();
    }

    @AfterEach
    public void tearDown() {
        campaignRepository.deleteAll();
        productRepository.deleteAll();
    }

    // Allow subclasses to perform additional setup if needed
    protected abstract void performAdditionalSetup();
}
