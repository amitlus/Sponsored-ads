package com.amit.sponsoredads.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
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
}






