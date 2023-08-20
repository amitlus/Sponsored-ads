package com.amit.sponsoredads.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product", indexes = {
        @Index(name = "idx_category", columnList = "category")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productSerialNumber; // Assuming productSerialNumber is the primary key
    private String title;
    private String category;
    private double price;
    @ManyToMany(mappedBy = "products")
    private List<Campaign> campaigns = new ArrayList<>();

    @Override
    public String toString() {
        return "Product{" +
                "productSerialNumber=" + productSerialNumber +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }

    //Override the equals method in order to use it in the tests, comparing based on field values and not based on memory addresses
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productSerialNumber.equals(product.productSerialNumber) &&
                Objects.equals(title, product.title) &&
                Objects.equals(category, product.category) &&
                Objects.equals(price, product.price);
    }

    //Can't override just the "equals" method, I need to override both equals and hashCode or none of them (Lombok @Data generates it)
    @Override
    public int hashCode() {
        return Objects.hash(productSerialNumber, title, category, price);
    }
}