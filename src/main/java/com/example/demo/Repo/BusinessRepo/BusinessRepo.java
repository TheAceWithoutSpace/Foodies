package com.example.demo.Repo.BusinessRepo;

import com.example.demo.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepo extends JpaRepository<Business,Long> {

}
