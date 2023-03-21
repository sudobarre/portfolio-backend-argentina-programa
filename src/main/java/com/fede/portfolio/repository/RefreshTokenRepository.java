package com.fede.portfolio.repository;

import com.fede.portfolio.model.RefreshToken;
import com.fede.portfolio.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);

    @Modifying
    int deleteByUser(User user);
}