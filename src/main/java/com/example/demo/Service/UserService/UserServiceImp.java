package com.example.demo.Service.UserService;

import com.example.demo.Repo.UserRepo.AppUserRepo;
import com.example.demo.Repo.UserRepo.RoleRepo;
import com.example.demo.Service.UserService.UserService;
import com.example.demo.model.AppUser;
import com.example.demo.model.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {
    private final AppUserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user=userRepo.findByUserName(username);
        if(user==null){
            log.error("User not found in the db");
            throw new UsernameNotFoundException("User not found in the db");
        }else{
            log.info("User were found in the db ");

        }
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
    }
    @Override
    public AppUser saveUser(AppUser user) {
       log.info("Added new user {} to the db",user);
       user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Added new role {} to the db",role);
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("Added new role:{} to  user:{}",roleName,userName);
        AppUser user=userRepo.findByUserName(userName);
        System.out.println(user);
        log.info("{}",user);
        Role role=roleRepo.findByName(roleName);
        log.info("{}",role);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String userName) {
        log.info("Fetch user{} from the db",userName);
        return userRepo.findByUserName(userName);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetch All users");
        return userRepo.findAll();
    }

}
