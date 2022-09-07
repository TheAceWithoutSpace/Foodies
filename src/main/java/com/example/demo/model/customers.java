package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class customers {
    @Id
    private long id;
    private String location;
    private String name;
    private String lastName;
    private String phoneNumber;
}
