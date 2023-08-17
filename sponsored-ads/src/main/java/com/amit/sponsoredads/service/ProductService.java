package com.amit.sponsoredads.service;

import com.amit.sponsoredads.dto.product.ProductDto;
import com.amit.sponsoredads.dto.product.ProductMapper;
import com.amit.sponsoredads.model.Campaign;
import com.amit.sponsoredads.model.Product;
import com.amit.sponsoredads.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto getProductByCategoryAndCampaign(String category, Campaign campaign){
        Product product = productRepository.findProductByCategoryAndCampaigns(category, campaign);
        return ProductMapper.INSTANCE.productToProductDto(product);
    }
}

