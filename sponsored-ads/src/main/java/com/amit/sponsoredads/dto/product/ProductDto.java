package com.amit.sponsoredads.dto.product;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @Size(min = 2)
    private String title;
    private String category;
    private double price;
}
