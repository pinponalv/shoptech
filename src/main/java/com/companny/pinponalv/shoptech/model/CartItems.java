package com.companny.pinponalv.shoptech.model;

import jakarta.persistence.*;

public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "cartItems_products", joinColumns = @JoinColumn(name = "cartItem_id"),
    inverseJoinColumns = @JoinColumn(name = "products_id"))
    private Products products;
    @Column(nullable = false)
    private Integer quantity;


}
