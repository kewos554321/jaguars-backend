package com.jaguars.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaguars.core.model.entity.TokenEntity;
import java.util.List;
import java.util.Optional;


public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    Optional<TokenEntity> findByUserEmail(String userEmail);

}