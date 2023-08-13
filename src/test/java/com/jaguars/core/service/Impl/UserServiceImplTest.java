package com.jaguars.core.service.Impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.jaguars.core.model.entity.UserEntity;
import com.jaguars.core.service.interfaces.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserServiceImplTest {

	@Autowired
	private UserService userService;

	// @Test
	// void testCreateUser() {
	// 	String email = "account5@example.com";
	// 	UserEntity user = userService.getUserByEmail(email);
	// 	user.setUuid(null);
	// 	user.setEmail("account55@example.com");
	// 	UserEntity entity = userService.createUser(user);
	// 	log.info("jay-entity={}", entity.toString());
	// }
	// @Test
	// void testgetUserByEmail() {
	// 	String email = "account5@example.com";
	// 	UserEntity entity = userService.getUserByEmail(email);
	// 	log.info("jay-entity={}", entity.toString());
	// }
	// @Test
	// void testGetAllUsers() {
	// 	List<UserEntity> entities = userService.getAllUsers();
	// 	log.info("jay-entities={}", entities.toString());
	// }
	// @Test
	// void testUpdateUser() {
	// 	String email = "account5@example.com";
	// 	UserEntity user = userService.getUserByEmail(email);
	// 	user.setUpdateDt(new Date());
	// 	user.setUpdateUser("Jay");
	// 	UserEntity entity = userService.updateUser(user);
	// 	log.info("jay-entity={}", entity.toString());
	// }
	// @Test
	// void testDeleteUserByEmail() {
	// 	String email = "account55@example.com";
	// 	userService.deleteUserByEmail(email);
	// 	log.info("jay-deleted");
	// }
}