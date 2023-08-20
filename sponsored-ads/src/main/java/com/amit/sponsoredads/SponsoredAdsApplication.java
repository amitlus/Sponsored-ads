package com.amit.sponsoredads;

import com.amit.sponsoredads.model.Campaign;
import com.amit.sponsoredads.model.Product;
import com.amit.sponsoredads.repository.CampaignRepository;
import com.amit.sponsoredads.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public void run(String... args) {
        //On initialization, you may generate a bulk of products, each with random category (select one from few possibilities)...
        List<String> categories = new ArrayList<>();
        categories.add("Sports");
        categories.add("Music");
        categories.add("Lifestyle");

        Random random = new Random();
        String randomCategory;
        int randomIndex;

        Product product1 = new Product();
        product1.setTitle("Product 1");
        randomIndex = random.nextInt(categories.size());
        randomCategory = categories.get(randomIndex);
        product1.setCategory(randomCategory);
        product1.setPrice(100.0);

        Product product2 = new Product();
        product2.setTitle("Product 2");
        randomIndex = random.nextInt(categories.size());
        randomCategory = categories.get(randomIndex);
        product2.setCategory(randomCategory);
        product2.setPrice(200.0);

        Product product3 = new Product();
        product3.setTitle("Product 3");
        randomIndex = random.nextInt(categories.size());
        randomCategory = categories.get(randomIndex);
        product3.setCategory(randomCategory);
        product3.setPrice(400.0);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        Campaign campaign1 = new Campaign();
        campaign1.setName("Campaign 1");
        campaign1.setStartDate(LocalDateTime.now());
        campaign1.setBid(50.0);
        campaign1.getProducts().add(product1);

        Campaign campaign2 = new Campaign();
        campaign2.setName("Campaign 2");
        campaign2.setStartDate(LocalDateTime.now());
        campaign2.setBid(75.0);
        campaign2.getProducts().add(product2);

        Campaign campaign3 = new Campaign();
        campaign3.setName("Campaign 3");
        campaign3.setStartDate(LocalDateTime.now());
        campaign3.setBid(100.0);
        campaign3.getProducts().add(product3);

        Campaign campaign4 = new Campaign();
        campaign3.setName("Campaign 4");
        campaign3.setStartDate(LocalDateTime.now().minusDays(11));
        campaign3.setBid(200.0);
        campaign3.getProducts().add(product1);

        campaignRepository.save(campaign1);
        campaignRepository.save(campaign2);
        campaignRepository.save(campaign3);
        campaignRepository.save(campaign4);

        // Associate the products with campaigns
        product1.getCampaigns().add(campaign1);
        product2.getCampaigns().add(campaign2);
        product3.getCampaigns().add(campaign3);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }
}

