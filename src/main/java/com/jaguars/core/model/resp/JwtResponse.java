package com.jaguars.core.model.resp;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -2834409284828426814L;

	private String token;
	private String refreshToken;

}