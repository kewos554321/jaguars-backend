package com.jaguars.core.service.interfaces;

import java.util.Optional;

import com.jaguars.core.model.entity.TokenEntity;

public interface TokenService {
	
	public void createTokenRecord(String token, String refeshToken, String uuid);

}
