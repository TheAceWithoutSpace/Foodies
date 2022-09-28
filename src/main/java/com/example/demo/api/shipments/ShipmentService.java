package com.example.demo.api.shipments;

import com.example.demo.api.shipments.shipments;

import java.util.List;
import java.util.Optional;

public interface ShipmentService {
    shipments saveShipment(shipments shipments);
    Optional<shipments> getShipment(Long shipmentId);

    Optional<shipments>updateCourier(Long courierId, Long shipmentId);

    Optional<shipments> Delivered(Long shipmentId);

    List<shipments>getAllShipments();
    List<shipments> getShipmentsByCourier(Long courierId);
    List<shipments> getShipmentsByBusiness(Long BusinessId);
    void setShipmentStatus(Long shipmentId);

}
