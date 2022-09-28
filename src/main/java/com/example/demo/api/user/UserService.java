package com.example.demo.api.user;

import com.example.demo.api.Business.Business;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    AppUser saveUser(AppUser user);
    boolean addRoleToUser(String userName,Role roleName);
    boolean addBusinessToUser(String userName,Long businessId);
    Optional<AppUser> getUser(String userName);
    boolean checkIfUserExists(String userName,String email,String phoneNumber);
    List<AppUser> getUsers();
    List<AppUser>getUsersWhereUserRoleIsNull();
    List<AppUser> getUsersWhereUserRoleIsBusiness();
    List<AppUser> getUsersWhereUserRoleIsUser();
    List<AppUser>getUsersWhereUserRoleIsCouriers();
    List<AppUser>getUsersWhereBusinessIsNull();
    List<AppUser> getUsersWhereBusinessIs(Long Id);
    String confirmToken(String token);

}
