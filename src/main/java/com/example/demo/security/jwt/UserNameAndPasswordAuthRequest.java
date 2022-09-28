package com.example.demo.security.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserNameAndPasswordAuthRequest {
    private String username;
    private String password;
}
