package com.example.demo.api.Courier;

import com.example.demo.api.user.AppUser;
import lombok.*;

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
@Getter
@Setter
public class courier {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @OneToOne(fetch = EAGER)
    private AppUser user;
    private Date courierStartOfEmployment=new Date();
    private boolean IsActive=true;

    public courier(AppUser user) {
        this.user = user;
    }
}
