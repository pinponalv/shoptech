package com.companny.pinponalv.shoptech.model;

import com.companny.pinponalv.shoptech.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(unique = true, nullable = false)
    private String numberOrder;
    @Column(nullable = false)
    private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany
    @JoinTable(name = "orders_orderitems", joinColumns = @JoinColumn(name = "orders_id"),
    inverseJoinColumns = @JoinColumn(name = "orderItems_id"))
    private OrderItems orderItems;
}
