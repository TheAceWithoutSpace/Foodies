package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class shipments {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long Id;
    private Long shipmentLocation;
    private Long shipmentContactNumber;
    private boolean isCredit;
    private float amount;
    private boolean isDelivered;
    private String comments;
    @OneToOne(fetch = EAGER)
    private customers customerId;
    @OneToOne(fetch = EAGER)
    private Business businessId;
    @OneToOne(fetch = EAGER)
    private courier courierId;
}
