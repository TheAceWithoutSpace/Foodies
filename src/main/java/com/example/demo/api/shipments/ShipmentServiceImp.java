package com.example.demo.api.shipments;

import com.example.demo.api.Courier.CourierService;
import com.example.demo.api.shipments.ShipmentRepo;
import com.example.demo.api.shipments.ShipmentService;
import com.example.demo.api.shipments.shipments;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ShipmentServiceImp implements ShipmentService {
    private final ShipmentRepo shipmentRepo;
    private final CourierService courierService;
    @Override
    public shipments saveShipment(shipments shipments) {
        System.out.println("&"+shipments);
        return shipmentRepo.save(shipments);
    }

    @Override
    public Optional<shipments> getShipment(Long shipmentId) {
        return shipmentRepo.findById(shipmentId);
    }

    @Override
    public Optional<shipments> updateCourier(Long courierId, Long shipmentId) {
        log.info(shipmentId+" "+courierId);
        System.out.println(new Date());
        shipmentRepo.shipmentsUpdateCourier(courierService.getCourierById(courierId).get(),shipmentId,new Date());
        return shipmentRepo.findById(shipmentId);
    }

    @Override
    public Optional<shipments> Delivered( Long shipmentId) {
        shipmentRepo.shipmentsFinished(shipmentId,new Date());
        return shipmentRepo.findById(shipmentId);
    }

    @Override
    public List<shipments> getAllShipments() {
        return shipmentRepo.findAll();
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
