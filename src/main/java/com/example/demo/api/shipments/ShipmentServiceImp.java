package com.example.demo.api.shipments;

import com.example.demo.api.Business.BusinessService;
import com.example.demo.api.Courier.CourierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.demo.api.shipments.ShipmentStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ShipmentServiceImp implements ShipmentService {
    private final ShipmentRepo shipmentRepo;
    private final CourierService courierService;
    private final BusinessService businessService;
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
        shipmentRepo.shipmentsUpdateCourier(courierService.getCourierById(courierId).orElseThrow(),shipmentId,new Date());
        shipmentRepo.SetShipmentStatus(shipmentId,new Date(),OnGoing);
        return shipmentRepo.findById(shipmentId);
    }

    @Override
    public Optional<shipments> cancelShipment(Long shipmentId) {
        shipmentRepo.SetShipmentStatus(shipmentId,new Date(),Canceled);
        return shipmentRepo.findById(shipmentId);
    }

    @Override
    public Optional<shipments> unsetCourier(Long shipmentId) {
        shipmentRepo.shipmentsUpdateCourier(null,shipmentId,new Date());
        shipmentRepo.SetShipmentStatus(shipmentId,new Date(),Pending);
        return shipmentRepo.findById(shipmentId);
    }

    @Override
    public Optional<shipments> Delivered( Long shipmentId) {
        shipmentRepo.shipmentsFinished(shipmentId,new Date(),Delivered);
        return shipmentRepo.findById(shipmentId);
    }

    @Override
    public List<shipments> getAllShipments() {
        return shipmentRepo.findAll();
    }

    @Override
    public List<shipments> getShipmentsByCourier(Long courierId) {
        return shipmentRepo.findAllByCourierId(courierService.getCourierById(courierId).orElseThrow());
    }

    @Override
    public List<shipments> getShipmentsByBusiness(Long BusinessId) {
        return shipmentRepo.findAllByBusinessId(businessService.getBusinessById(BusinessId).orElseThrow());
    }

    @Override
    public void setShipmentStatus(Long shipmentId) {

    }
}
