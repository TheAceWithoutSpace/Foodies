package com.example.demo.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;
    public String getPermission(){return permission;}
}
