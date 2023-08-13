package com.jaguars.core.exception;

import java.util.List;

import com.jaguars.core.enumerate.ErrorCodeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorCodeException extends RuntimeException {
	
	private static final long serialVersionUID = -8932595907926333782L;

	private ErrorCodeEnum errorCode;
	private String errorMsg;
	private List<String> datas;

	public ErrorCodeException(ErrorCodeEnum errorCodeEnum) {
		this.errorCode = errorCodeEnum;
	}

	public ErrorCodeException(ErrorCodeEnum errorCodeEnum, String errorMsgg) {
		this.errorCode = errorCodeEnum;
		this.errorMsg = errorMsgg;
	}
	
	public ErrorCodeException(ErrorCodeEnum errorCodeEnum, List<String> datas) {
        this.errorCode = errorCodeEnum;
        this.datas = datas;
    }

}
