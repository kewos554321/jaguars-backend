package com.jaguars.core.configuration.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jaguars.core.enumerate.UserErrorCodeEnumImpl;
import com.jaguars.core.exception.ErrorCodeException;
import com.jaguars.core.model.entity.UserEntity;
import com.jaguars.core.repository.UserRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String loginAccount) throws UsernameNotFoundException {
		Optional<UserEntity> userOptional = userRepository.findByAccount(loginAccount);
		if (userOptional.isPresent()) {
		    
		    if(Integer.compare(userOptional.get().getIsActivated(), 0) == 0) {
                throw new ErrorCodeException(UserErrorCodeEnumImpl.USER_IS_NOT_ACTIVATED);
            }
		    
			return new User(userOptional.get().getAccount(),userOptional.get().getPassword() ,
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with loginAccount: " + loginAccount);
		}
	}
	 
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return new User(userOptional.get().getEmail(), userOptional.get().getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
	
	
	// public UserEntity save(CreateUserRequest user) {
	// 	UserEntity newUser = new UserEntity();
	// 	newUser.setLoginAccount(user.getLoginAccount());
	// 	newUser.setLoginSecretCode(bcryptEncoder.encode(user.getLoginSecretCode()));
	// 	newUser.setUserName(user.getUserName());
	// 	newUser.setEmail(user.getEmail());
	// 	newUser.setStatus("I");
	// 	return userRepository.save(newUser);
	// }
	
	// public boolean checkUserPxd(String pxd,String loginSecretCode) {
	// 	return bcryptEncoder.matches(pxd, loginSecretCode);
	// }
	
}