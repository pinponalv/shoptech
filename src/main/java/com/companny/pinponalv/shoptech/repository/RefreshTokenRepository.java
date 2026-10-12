package com.companny.pinponalv.shoptech.repository;

import com.companny.pinponalv.shoptech.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    void deleteByExpiresAtBefore(LocalDateTime dateTime);
}
