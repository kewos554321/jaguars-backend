package com.jaguars.core.enumerate;

public enum UserErrorCodeEnumImpl implements ErrorCodeEnum {
	USER_NOT_FOUND("0001"),
	PASSWORD_NOT_SAME("0002"),
	USER_IS_NOT_ACTIVATED("0003"),
	USER_EXISTS_CREATION_BLOCKED("0004"),
	;

	private String code;
	
	private UserErrorCodeEnumImpl(String errorCode) {
		this.code = errorCode;
	}

	@Override
	public String getCategory() {
		return "User";
	}

	@Override
	public String getCode() {
		return this.code;
	}

}
