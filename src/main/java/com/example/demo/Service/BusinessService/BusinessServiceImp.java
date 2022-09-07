package com.example.demo.Service.BusinessService;

import com.example.demo.Repo.BusinessRepo.BusinessRepo;
import com.example.demo.model.Business;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BusinessServiceImp implements BusinessService{

    private final BusinessRepo businessRepo;
    @Override
    public Business getBusiness(Long id) {
        return null;
    }

    @Override
    public List<Business> getAllBusiness() {
        return null;
    }

    @Override
    public void saveBusiness(Business business) {

    }
}
