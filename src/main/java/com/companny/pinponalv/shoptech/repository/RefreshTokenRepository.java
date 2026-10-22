package com.companny.pinponalv.shoptech.repository;

import com.companny.pinponalv.shoptech.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    void deleteByExpiresAtBefore(LocalDateTime dateTime);
}
