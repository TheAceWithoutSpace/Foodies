package com.example.demo.api.Business;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessRepo extends JpaRepository<Business,Long> {

    Optional<Business>findByBusinessName(String businessName);
}
