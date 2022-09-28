package com.example.demo.api.Courier;

import com.example.demo.api.user.AppUser;
import com.example.demo.api.Courier.courier;

import java.util.List;
import java.util.Optional;

public interface CourierService {

     Optional<courier> getCourier(AppUser user);
    List<courier> getAllCouriers();

    Optional<courier> getCourierById(Long courierId);
    courier saveCourier(courier courier);
}
