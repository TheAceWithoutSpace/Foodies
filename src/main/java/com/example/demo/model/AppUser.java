package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private String lastName;
    @Column(unique=true)
    private String userName;
    private String password;
    @Column(unique=true)
    private String phoneNumber;
    private String notificationToken;
    private boolean isDeleted;
    private Date creationDate;
    private Date modifiedDate;
    @ManyToMany(fetch = EAGER)
    private Collection<Role>roles =new ArrayList<>();
}
