package com.example.demo.Service.ShipmentService;

import com.example.demo.model.shipments;

import java.util.List;

public interface ShipmentService {
    shipments saveShipment(shipments shipments);
    shipments getShipment(Long shipmentId);

    List<shipments>getAllShipments();
    List<shipments> getShipmentsByCourier(Long courierId);
    List<shipments> getShipmentsByBusiness(Long BusinessId);
    void setShipmentStatus(Long shipmentId);

}
