package com.example.demo.api;

import com.example.demo.Service.BusinessService.BusinessService;
import com.example.demo.model.Business;
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
public class BusinessController {
    private final BusinessService businessService;
    @GetMapping("/business")
    public List<Business> GetAllBuisness(){
        return businessService.getAllBusiness();
    }
}
