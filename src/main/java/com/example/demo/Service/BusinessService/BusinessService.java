package com.example.demo.Service.BusinessService;

import com.example.demo.model.Business;

import java.util.List;

public interface BusinessService {
    Business getBusiness(Long id);
    List<Business> getAllBusiness();
    void saveBusiness(Business business);
}
