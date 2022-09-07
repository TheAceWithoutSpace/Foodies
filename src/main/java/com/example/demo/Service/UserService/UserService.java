package com.example.demo.Service.UserService;

import com.example.demo.model.AppUser;
import com.example.demo.model.Role;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String userName,String roleName);
    AppUser getUser(String userName);
    List<AppUser> getUsers();
}
