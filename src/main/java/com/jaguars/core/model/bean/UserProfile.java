package com.jaguars.core.model.bean;

import lombok.Data;

@Data
public class UserProfile {
    private String uuid;
	private String name;
	private String email;
	private String type;
}
