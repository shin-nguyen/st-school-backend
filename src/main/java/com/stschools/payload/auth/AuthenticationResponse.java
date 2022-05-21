package com.stschools.payload.auth;

import lombok.Data;

import java.util.Date;

@Data
public class AuthenticationResponse {
    private String email;
    private String token;
    private String userRole;
}
