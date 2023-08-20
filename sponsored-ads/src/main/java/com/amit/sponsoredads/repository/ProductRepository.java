package com.amit.sponsoredads.repository;

import com.amit.sponsoredads.model.Campaign;
import com.amit.sponsoredads.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findFirstByCategoryAndCampaigns(String category, Campaign campaign);

    Product findProductByProductSerialNumber(Integer productSerialNumber);
}
