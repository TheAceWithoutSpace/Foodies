package com.example.demo.Service.CourierService;

import com.example.demo.model.courier;

import java.util.List;

public interface CourierService {

    courier getCourier(Long courierId);
    List<courier> getAllCouriers();
    void saveCourier(courier courier);
}
