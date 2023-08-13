package com.jaguars.core.model.resp;

import java.io.Serializable;

import com.jaguars.core.model.bean.UserProfile;

import lombok.Data;

@Data
public class AuthorizationResponse implements Serializable {

	private static final long serialVersionUID = -2834409284828426814L;

	private UserProfile userProfile;

}