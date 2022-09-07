package com.example.demo.Repo.CustomerRepo;

import com.example.demo.model.customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<customers,Long> {
}
