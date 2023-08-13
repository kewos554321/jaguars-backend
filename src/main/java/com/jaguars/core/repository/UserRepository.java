package com.jaguars.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaguars.core.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public Optional<UserEntity> findByUuid(String uuid);

	public Optional<UserEntity> findByAccount(String account);
	
	public Optional<UserEntity> findByEmail(String email);

}