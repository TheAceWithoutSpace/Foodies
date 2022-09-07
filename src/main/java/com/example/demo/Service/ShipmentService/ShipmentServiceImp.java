package com.example.demo.Service.ShipmentService;

import com.example.demo.Repo.ShipmentRepo.ShipmentRepo;
import com.example.demo.model.shipments;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ShipmentServiceImp implements ShipmentService{
    private final ShipmentRepo shipmentRepo;
    @Override
    public shipments saveShipment(shipments shipments) {
        return null;
    }

    @Override
    public shipments getShipment(Long shipmentId) {
        return null;
    }

    @Override
    public List<shipments> getAllShipments() {
        return null;
    }

    @Override
    public List<shipments> getShipmentsByCourier(Long courierId) {
        return null;
    }

    @Override
    public List<shipments> getShipmentsByBusiness(Long BusinessId) {
        return null;
    }

    @Override
    public void setShipmentStatus(Long shipmentId) {

    }
}
