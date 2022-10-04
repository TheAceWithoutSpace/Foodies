package com.example.demo.api.shipments;

import com.example.demo.api.Business.BusinessService;
import com.example.demo.api.Courier.CourierService;
import com.example.demo.api.Customer.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
@Slf4j
public class ShipmentController {
    private final ShipmentService shipmentService;
    private final CustomerService customerService;
    private final BusinessService businessService;
    @GetMapping("/shipment")
    ResponseEntity<List<shipments>>getAllShipments(){
        return ResponseEntity.ok().body(shipmentService.getAllShipments());
    }
    @GetMapping("/shipmentByBusiness")
    ResponseEntity<List<shipments>> shipmentByBusiness(@RequestParam Long BusinessId){
        return ResponseEntity.ok().body(shipmentService.getShipmentsByBusiness(BusinessId));
    }
    @GetMapping("/shipmentByCourier")
    ResponseEntity<List<shipments>> shipmentByCourier(@RequestParam Long CourierId){
        return ResponseEntity.ok().body(shipmentService.getShipmentsByCourier(CourierId));
    }
    @PostMapping("/shipmentSetCourier")
    public ResponseEntity<shipments> SetCourier(@RequestParam Long courierId,Long shipmentId){
        return ResponseEntity.ok().body(shipmentService.updateCourier(courierId,shipmentId).orElseThrow());
    }
    @PostMapping("/shipmentUnsetCourier")
    public ResponseEntity<shipments> UnsetCourier(@RequestParam Long shipmentId){
            return ResponseEntity.ok().body(shipmentService.unsetCourier(shipmentId).orElseThrow());
    }
    @PostMapping("/cancelShipment")
    public ResponseEntity<shipments> cancelShipment(@RequestParam Long shipmentId){
        return ResponseEntity.ok().body(shipmentService.cancelShipment(shipmentId).orElseThrow());
    }

    @PostMapping("/shipmentSetFinished")
    public ResponseEntity<shipments> setFinished(@RequestParam Long shipmentId){
        if(shipmentService.getShipment(shipmentId).isPresent()){
            if(shipmentService.Delivered(shipmentId).isPresent()){
                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.status(405).build();
            }
        }else {
            return ResponseEntity.status(404).build();
        }
    }
    @PostMapping("/shipment")
    public ResponseEntity<shipments> createShipment(@RequestParam Long businessNumber,@RequestBody shipments shipment){
        shipments transshipment= new shipments(shipment.getShipmentLocation(),
                                shipment.getShipmentContactNumber(),shipment.isCredit(),
                                shipment.getAmount(),shipment.getComments(),shipment.getShipmentToPickUpDate(),
                                customerService.getCustomer(shipment.getShipmentContactNumber()).orElseThrow(),
                                businessService.getBusinessById(businessNumber).orElseThrow()
                );
        shipmentService.saveShipment(transshipment);
        return ResponseEntity.ok().build();
    }
}
