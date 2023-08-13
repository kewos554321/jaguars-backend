package com.jaguars.core.model.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = -4759388262050468119L;

    @ApiModelProperty(example = "user@gmail.com")
    private String email;
    
    @ApiModelProperty(example = "password")
    private String password;
}