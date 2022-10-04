package com.example.demo.api.user;

import com.example.demo.api.Business.Business;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import static com.example.demo.api.user.Role.NewUser;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppUser implements UserDetails {
    @SequenceGenerator(
            name="appUser_sequence",
            sequenceName = "appUser_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = SEQUENCE,
            generator ="appUser_sequence")
    private Long id;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private String phoneNumber;
    private String notificationToken=null;
    private boolean isDeleted =false;
    private Date creationDate= new Date();
    private Date modifiedDate=null;
    private Boolean locked =false;
    private Boolean enabled =false;
    @Enumerated(EnumType.STRING)
    private Role roles=NewUser;

    @OneToOne(fetch = EAGER)
    private Business business=null;

    public AppUser(String firstName,
                   String lastName,
                   String username,
                   String email,
                   String password,
                   String phoneNumber,
                   String notificationToken) {
        this.name = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.notificationToken = notificationToken;
        this.roles=NewUser;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority= new SimpleGrantedAuthority(roles.name());
        return Collections.singleton(authority);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

