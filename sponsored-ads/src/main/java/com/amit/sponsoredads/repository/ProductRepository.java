package com.amit.sponsoredads.repository;

import com.amit.sponsoredads.model.Campaign;
import com.amit.sponsoredads.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductsByProductSerialNumberIn(List<Integer> productIds);
    Product findProductByCategoryAndCampaigns(String category, Campaign campaign);
    Product findProductByProductSerialNumber(Integer productSerialNumber);
}
