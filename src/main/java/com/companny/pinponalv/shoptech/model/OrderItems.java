package com.companny.pinponalv.shoptech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "orderdetails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItems {

    //TODO: FALTA GENRAR RELACIONES ENTRE MIS ENTIDADES

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "orderItems_product",  joinColumns = @JoinColumn(name = "orderitems_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Products products;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private BigDecimal unitPrice;
    @Column(nullable = false)
    private BigDecimal totalPrice;
}
