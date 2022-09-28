package com.example.demo.api.shipments;

import com.example.demo.api.Business.BusinessService;
import com.example.demo.api.Customer.CustomerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @PostMapping("/shipmentSetCourier")
    public ResponseEntity<shipments> SetCourier(@RequestParam Long courierId,Long shipmentId){
        if(shipmentService.getShipment(shipmentId).isPresent()){

            if(shipmentService.updateCourier(courierId,shipmentId).isPresent()) {
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.status(405).build();
            }
        }else {
            return ResponseEntity.status(404).build();
        }
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
    public ResponseEntity<shipments> createShipment(@RequestBody createShipment shipment){
        System.out.println("hello");
        if(customerService.getCustomer(shipment.getCustomerPhoneNumber()).isPresent()&&businessService.getBusiness(shipment.getBusinessName()).isPresent()){
            shipments shipment1=new shipments(shipment.getShipmentLocation(),
                    shipment.getShipmentContactNumber(),shipment.isCredit(),
                    shipment.getAmount(),shipment.isDelivered(),shipment.getComments(),
                    shipment.getShipmentToPickUpDate(),
                    customerService.getCustomer(shipment.getCustomerPhoneNumber()).get(),
                    businessService.getBusiness(shipment.getBusinessName()).get(),
                    new Date()
            );
            return ResponseEntity.ok().body(shipmentService.saveShipment(shipment1));
        }else{
            return ResponseEntity.status(405).build();
        }
    }
}

@Getter
@Setter
class createShipment{
    private String shipmentLocation;
    private String shipmentContactNumber;
    private boolean isCredit;
    private Long amount;
    private boolean isDelivered;
    private String comments;
    private Date shipmentToPickUpDate;
    private String customerPhoneNumber;
    private String businessName;
    private String courierName;
}
