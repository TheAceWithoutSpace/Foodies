package com.example.demo.api;

import com.example.demo.Service.CourierService.CourierService;
import com.example.demo.model.Business;
import com.example.demo.model.courier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
@Slf4j
public class CourierController {
    private final CourierService courierService;
    @GetMapping("/courier")
    public List<courier> GetAllcourier(){
        return courierService.getAllCouriers();
    }
}
