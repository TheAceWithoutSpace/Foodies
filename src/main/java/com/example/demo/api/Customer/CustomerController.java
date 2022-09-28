package com.example.demo.api.Customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
@Slf4j
public class CustomerController {
    private final CustomerService customerService;
   @GetMapping("/customers")
    public List<customers>getAllCustomers(){
       return customerService.getAllCustomers();
   }
   @GetMapping("/customer")
    public Optional<List<customers>> getCustomerService(@RequestParam String phoneNumber) {
        return customerService.findMacthCustomeresByPhoneNumber(phoneNumber) ;
    }

    @PostMapping("/customers")
    public ResponseEntity<customers> saveUser(@RequestBody customers customers){
        System.out.println("$"+customers);
        return ResponseEntity.ok().body(customerService.saveCustomer(customers));
    }
}
