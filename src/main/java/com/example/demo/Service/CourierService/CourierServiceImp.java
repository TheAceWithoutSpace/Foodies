package com.example.demo.Service.CourierService;

import com.example.demo.Repo.CourierRepo.CourierRepo;
import com.example.demo.model.courier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CourierServiceImp implements CourierService{

    private final CourierRepo courierRepo;
    @Override
    public courier getCourier(Long courierId) {
        return null;
    }

    @Override
    public List<courier> getAllCouriers() {
        return null;
    }

    @Override
    public void saveCourier(courier courier) {

    }
}
