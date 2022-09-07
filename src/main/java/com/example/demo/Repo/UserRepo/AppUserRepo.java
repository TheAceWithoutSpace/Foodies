package com.example.demo.Repo.UserRepo;

import com.example.demo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByName(String userName);
    AppUser findByUserName(String userName);
}
