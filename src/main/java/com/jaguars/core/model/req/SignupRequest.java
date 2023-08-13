package com.jaguars.core.model.req;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
}
