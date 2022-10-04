package com.example.demo.api.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<List<customers>> findMathCustomersByPhoneNumber(String phoneNumber);
    Optional<customers> getCustomer(String phoneNumber);
    List<customers> getAllCustomers();
    customers saveCustomer(customers customer);

}
