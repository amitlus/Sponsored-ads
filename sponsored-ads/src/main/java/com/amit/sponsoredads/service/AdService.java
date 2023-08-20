package com.amit.sponsoredads.service;

import com.amit.sponsoredads.dto.product.ProductDto;
import com.amit.sponsoredads.dto.product.ProductMapper;
import com.amit.sponsoredads.model.Campaign;
import com.amit.sponsoredads.model.Product;
import com.amit.sponsoredads.repository.ProductRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class AdService {
    private ProductRepository productRepository;

    public AdService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto findProductByCategoryAndCampaign(String category, Campaign campaign) {
        Product product = productRepository.findProductByCategoryAndCampaigns(category, campaign);
        return ProductMapper.INSTANCE.productToProductDto(product);
    }

    public Product findProductByProductSerialNumber(Integer productSerialNumber) {
        return productRepository.findProductByProductSerialNumber(productSerialNumber);
    }


}

