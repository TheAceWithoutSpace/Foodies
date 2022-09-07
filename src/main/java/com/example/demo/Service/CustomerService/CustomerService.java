package com.example.demo.Service.CustomerService;

import com.example.demo.model.customers;

import java.util.List;

public interface CustomerService {

    customers getCustomer(Long customerId);
    List<customers> getAllCustomers();
    void saveUser(customers customer);

}
