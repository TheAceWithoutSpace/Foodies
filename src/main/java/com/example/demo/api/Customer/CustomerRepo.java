package com.example.demo.api.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface CustomerRepo extends JpaRepository<customers,Long> {
    Optional<customers>findByPhoneNumber(String phoneNumber);
    Optional<List<customers>> findByName(String name);
    @Query(nativeQuery = true, value = "SELECT * FROM customers  WHERE phone_number LIKE (:phoneNumber%)")
    Optional<List<customers>> findAllByPhoneNumber(final String phoneNumber);
}
