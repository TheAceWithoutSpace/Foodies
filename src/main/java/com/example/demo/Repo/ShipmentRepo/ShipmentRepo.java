package com.example.demo.Repo.ShipmentRepo;

import com.example.demo.model.shipments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepo extends JpaRepository<shipments,Long> {
}
