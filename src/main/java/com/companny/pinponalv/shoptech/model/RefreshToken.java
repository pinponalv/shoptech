package com.companny.pinponalv.shoptech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private UserSec userSec;
    @Column(nullable = false)
    private LocalDateTime expireAt;
    @Column(nullable = false)
    private boolean revoked = false;
    @Column(nullable = false)
    private LocalDateTime createdAt =  LocalDateTime.now();
}
