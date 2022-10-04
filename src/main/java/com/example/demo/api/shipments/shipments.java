package com.example.demo.api.shipments;

import com.example.demo.api.Business.Business;
import com.example.demo.api.Courier.courier;
import com.example.demo.api.Customer.customers;
import lombok.*;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class shipments {
    @SequenceGenerator(
            name="shipments_sequence",
            sequenceName = "shipments_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = SEQUENCE,
            generator ="shipments_sequence")
    private Long Id;
    //the origin Location of the shipment
    private String shipmentLocation;
    //The customer PhoneNumber for the shipment to be delivered
    private String shipmentContactNumber;
    //does the shipment credit
    private boolean isCredit;
    //if isCredit true amount will be 0 else will point the amount that the courier need to pay the business for the delivery
    private float amount;
    //point that the shipment delivered /on the way
    private String comments;
    //the customer that ordered the shipment
    @OneToOne(fetch = EAGER)
    private customers customerId;
    //the  business that the shipment is going from
    @OneToOne(fetch = EAGER)
    private Business businessId;
    //the courier
    @OneToOne(fetch = EAGER)
    private courier courierId;
    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus=ShipmentStatus.Pending;
    //shipment picked up date will be null on creation and on picked up will get value
    private Date shipmentPickedUpDate=null;
    //the time that the business tell the courier to pick -up the shipment
    private Date shipmentToPickUpDate=null;
    //the creation time of the shipment;
    private Date shipmentCreationDate = new Date();
    //the date that the shipment delivered;
    private Date shipmentFinishedDate =null;
    public shipments(String shipmentLocation,
                     String shipmentContactNumber,
                     boolean isCredit,
                     float amount,
                     String comments, Date shipmentToPickUpDate,
                     customers customerId,
                     Business businessId) {
        this.shipmentLocation = shipmentLocation;
        this.shipmentContactNumber = shipmentContactNumber;
        this.isCredit = isCredit;
        this.amount = amount;
        this.comments = comments;
        this.shipmentToPickUpDate = shipmentToPickUpDate;
        this.customerId = customerId;
        this.businessId = businessId;
    }

//    public shipments(String shipmentLocation,
//                     String shipmentContactNumber,
//                     boolean isCredit,
//                     float amount,
//                     String comments, Date shipmentToPickUpDate,
//                     customers customerId,
//                     Business businessId, Date shipmentCreationDate) {
//        this.shipmentLocation = shipmentLocation;
//        this.shipmentContactNumber = shipmentContactNumber;
//        this.isCredit = isCredit;
//        this.amount = amount;
//        this.comments = comments;
//        this.shipmentToPickUpDate = shipmentToPickUpDate;
//        this.shipmentPickedUpDate = null;//will be determent after the shipment picked up;
//        this.shipmentCreationDate =shipmentCreationDate;
//        this.shipmentFinishedDate = null; //will be determent after the shipment finished;
//        this.customerId = customerId;
//        this.businessId = businessId;
//    }
}
