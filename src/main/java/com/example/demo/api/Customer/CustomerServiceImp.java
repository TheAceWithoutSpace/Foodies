package com.example.demo.api.Customer;

import com.example.demo.api.Customer.CustomerRepo;
import com.example.demo.api.Customer.CustomerService;
import com.example.demo.api.Customer.customers;
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
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepo customerRepo;

    @Override
    public Optional<List<customers>> findMathCustomersByPhoneNumber(String phoneNumber) {
        return customerRepo.findAllByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<customers> getCustomer(String phoneNumber) {
        return customerRepo.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<customers> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public customers saveCustomer(customers customer) {
        return customerRepo.save(customer);
    }
}
