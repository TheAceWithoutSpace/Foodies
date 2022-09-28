package com.example.demo.api.Courier;

import com.example.demo.api.Courier.CourierRepo;
import com.example.demo.api.Courier.CourierService;
import com.example.demo.api.user.AppUser;
import com.example.demo.api.Courier.courier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CourierServiceImp implements CourierService {

    private final CourierRepo courierRepo;
    public Optional<courier> getCourier(AppUser user) {
        return courierRepo.findByUser(user);
    }

    @Override
    public List<courier> getAllCouriers() {
        return courierRepo.findAll();
    }

    @Override
    public Optional<courier> getCourierById(Long courierId) {
        return courierRepo.findById(courierId);
    }

    @Override
    public courier saveCourier(courier courier) {
        return courierRepo.save(courier);
    }
}
