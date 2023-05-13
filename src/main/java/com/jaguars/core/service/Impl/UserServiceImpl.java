package com.jaguars.core.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jaguars.core.model.entity.UserEntity;
import com.jaguars.core.repository.UserRepository;
import com.jaguars.core.service.interfaces.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserEntity createUser(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity updateUser(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUserByEmail(String email) {
		UserEntity entity = getUserByEmail(email);
		userRepository.delete(entity);
	}
}