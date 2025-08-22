package com.Payment.Payment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @Column(name = "product_id", length = 36)
    private String productId;
    private String name;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
}
