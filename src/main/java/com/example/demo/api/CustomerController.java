package com.example.demo.api;

import com.example.demo.Service.CustomerService.CustomerService;
import com.example.demo.model.Business;
import com.example.demo.model.customers;
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
public class CustomerController {
    private final CustomerService customerService;
   @GetMapping("/costomers")
    public List<customers>getAllCustomers(){
       return customerService.getAllCustomers();
   }
}
