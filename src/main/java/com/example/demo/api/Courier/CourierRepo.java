package com.example.demo.api.Courier;

import com.example.demo.api.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface CourierRepo extends JpaRepository<courier,Long> {
    Optional<courier>findByUser(AppUser user);
}
