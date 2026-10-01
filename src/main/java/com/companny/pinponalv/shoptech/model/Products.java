package com.companny.pinponalv.shoptech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private String sku;
    @Column(nullable = false)
    private Boolean available;
    @Column(nullable = false)
    private Integer stock;
    private LocalDateTime createdAt =  LocalDateTime.now();
    @Column(nullable = false)
    private String productImageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "products_categorys", joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Categorys category;
}
