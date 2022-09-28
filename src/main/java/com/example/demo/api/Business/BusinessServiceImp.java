package com.example.demo.api.Business;

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
public class BusinessServiceImp implements BusinessService {

    private final BusinessRepo businessRepo;
    @Override
    public Optional<Business> getBusiness(String businessName) {
        return(businessRepo.findByBusinessName(businessName));
    }
    public Optional<Business> getBusinessById(Long id){
        return businessRepo.findById(id);
    }

    @Override
    public List<Business> getAllBusiness() {
        return businessRepo.findAll();
    }

    @Override
    public Business saveBusiness(Business business) {
        return businessRepo.save(business);
    }
}
