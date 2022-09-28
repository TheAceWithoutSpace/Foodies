package com.example.demo.api.Business;

import com.example.demo.api.Business.Business;

import java.util.List;
import java.util.Optional;

public interface BusinessService {
    Optional<Business> getBusinessById(Long id);
    Optional<Business> getBusiness(String businessName);
    List<Business> getAllBusiness();
    Business saveBusiness(Business business);
}
