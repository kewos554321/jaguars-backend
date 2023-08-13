package com.jaguars.core.service.Impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaguars.core.model.entity.TokenEntity;
import com.jaguars.core.repository.TokenRepository;
import com.jaguars.core.service.interfaces.TokenService;
import com.jaguars.core.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
    private JwtUtil jwtUtil;

	@Override
	public void createTokenRecord(String token, String refreshToken, String userEmail) {

		Optional<TokenEntity> entityOptional = tokenRepository.findByUserEmail(userEmail);
		TokenEntity entity = null;
		if (entityOptional.isPresent()) {
			entity = entityOptional.get();
			// entity.setToken(token);
			entity.setTokenExpirationDt(jwtUtil.extractExpiration(token));
			// entity.setRefreshToken(refreshToken);
			entity.setRefreshTokenExpirationDt(jwtUtil.extractExpiration(refreshToken));
			entity.setUpdateDt(new Date());
			tokenRepository.save(entity);
		} else {
			entity = new TokenEntity();
			// entity.setId(null);
			entity.setUserEmail(userEmail);
			// entity.setToken(token);
			entity.setTokenExpirationDt(jwtUtil.extractExpiration(token));
			// entity.setRefreshToken(refreshToken);
			entity.setRefreshTokenExpirationDt(jwtUtil.extractExpiration(refreshToken));
			entity.setCreateUser("system");
			entity.setCreateDt(new Date());
			entity.setUpdateUser("system");
			entity.setUpdateDt(new Date());
			tokenRepository.save(entity);
		}
		
		
	}

}