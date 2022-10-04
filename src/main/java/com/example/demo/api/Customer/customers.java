package com.example.demo.api.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String location;
    private String name;
    private String lastName;
    private String phoneNumber;

    public customers(String location,
                     String name,
                     String lastName,
                     String phoneNumber) {
        this.location = location;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
