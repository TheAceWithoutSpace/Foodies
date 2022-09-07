package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class courier {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @OneToOne(fetch = EAGER)
    private AppUser userId;
    private Date courierStartOfEmployment;
    private boolean IsActive;


}
