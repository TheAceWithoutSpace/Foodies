package com.example.demo.api.Business;

import com.example.demo.api.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Business {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String businessName;
    private String Location;
    @Column(unique = true)
    private String businessPhoneNumber;
    private String OwnerName;

    public Business(String businessName, String location, String businessPhoneNumber,String OwnerName) {
        this.businessName = businessName;
        this.Location = location;
        this.businessPhoneNumber = businessPhoneNumber;
        this.OwnerName=OwnerName;
    }
}
