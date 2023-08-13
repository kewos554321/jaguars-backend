package com.jaguars.core.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.jaguars.core.model.entity.UserEntity;
import com.jaguars.core.model.req.SignupRequest;

public interface UserService {
	
	public UserEntity createUser(SignupRequest request);
	
	public Optional<UserEntity> getUserByEmail(String email);

	public List<UserEntity> getAllUsers();

	public UserEntity updateUser(UserEntity user);

	public void deleteUserByEmail(String email);

}
