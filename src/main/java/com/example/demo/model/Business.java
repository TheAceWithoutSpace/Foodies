package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Business {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String businessName;
    private String Location;
    private String businessPhoneNumber;
    @OneToOne(fetch = EAGER)
    private AppUser userId;
}
