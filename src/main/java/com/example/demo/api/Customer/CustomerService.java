package com.example.demo.api.Customer;

import com.example.demo.api.Customer.customers;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<List<customers>>findMacthCustomeresByPhoneNumber(String phoneNumber);
    Optional<List<customers>> getCustomers(String name);
    Optional<customers> getCustomer(String phoneNumber);
    List<customers> getAllCustomers();
    customers saveCustomer(customers customer);

}
