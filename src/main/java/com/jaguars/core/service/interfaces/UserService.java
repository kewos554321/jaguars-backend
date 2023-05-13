package com.jaguars.core.service.interfaces;

import java.util.List;

import com.jaguars.core.model.entity.UserEntity;

public interface UserService {
	
	public UserEntity createUser(UserEntity user);
	
	public UserEntity getUserByEmail(String email);

	public List<UserEntity> getAllUsers();

	public UserEntity updateUser(UserEntity user);

	public void deleteUserByEmail(String email);

}
