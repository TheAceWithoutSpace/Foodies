package com.example.demo.api;

import com.example.demo.Service.ShipmentService.ShipmentService;
import com.example.demo.model.shipments;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
@Slf4j
public class ShipmentController {
    private final ShipmentService shipmentService;
    @GetMapping("/shipment")
    List<shipments>getAllShipments(){
        return shipmentService.getAllShipments();
    }
}
