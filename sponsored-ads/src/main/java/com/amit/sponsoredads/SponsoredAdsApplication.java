package com.amit.sponsoredads;

import com.amit.sponsoredads.model.Campaign;
import com.amit.sponsoredads.model.Product;
import com.amit.sponsoredads.repository.CampaignRepository;
import com.amit.sponsoredads.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SponsoredAdsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SponsoredAdsApplication.class, args);
    }

    private ProductRepository productRepository;
    private CampaignRepository campaignRepository;

    public SponsoredAdsApplication(ProductRepository productRepository, CampaignRepository campaignRepository) {
        this.productRepository = productRepository;
        this.campaignRepository = campaignRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Product product1 = new Product();
        product1.setTitle("Product 1");
        product1.setCategory("Category1");
        product1.setPrice(100.0);

        Product product2 = new Product();
        product2.setTitle("Product 2");
        product2.setCategory("Category2");
        product2.setPrice(200.0);

        Product product3 = new Product();
        product1.setTitle("Product 3");
        product1.setCategory("Category1");
        product1.setPrice(400.0);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        Campaign campaign1 = new Campaign();
        campaign1.setName("Campaign 1");
        campaign1.setStartDate(new Date());
        campaign1.setBid(50.0);
        campaign1.getProducts().add(product1);

        Campaign campaign2 = new Campaign();
        campaign2.setName("Campaign 2");
        campaign2.setStartDate(new Date());
        campaign2.setBid(75.0);
        campaign2.getProducts().add(product2);

        Campaign campaign3 = new Campaign();
        campaign3.setName("Campaign 3");
        campaign3.setStartDate(new Date());
        campaign3.setBid(100.0);
        campaign3.getProducts().add(product3);

        campaignRepository.save(campaign1);
        campaignRepository.save(campaign2);
        campaignRepository.save(campaign3);

        // Associate the products with campaigns
        product1.getCampaigns().add(campaign1);
        product2.getCampaigns().add(campaign2);
        product3.getCampaigns().add(campaign3);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }
}

