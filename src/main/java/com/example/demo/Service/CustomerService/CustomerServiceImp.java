package com.example.demo.Service.CustomerService;

import com.example.demo.Repo.CustomerRepo.CustomerRepo;
import com.example.demo.model.customers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CustomerServiceImp implements CustomerService{
    private final CustomerRepo customerRepo;
    @Override
    public customers getCustomer(Long customerId) {
        return null;
    }

    @Override
    public List<customers> getAllCustomers() {
        return null;
    }

    @Override
    public void saveUser(customers customer) {

    }
}
