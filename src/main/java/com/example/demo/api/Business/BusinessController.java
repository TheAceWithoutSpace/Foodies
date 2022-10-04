package com.example.demo.api.Business;

import com.example.demo.api.user.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
@Slf4j
public class BusinessController {
    private final BusinessService businessService;

    @GetMapping("/business")
    public List<Business> GetAllBusiness(){
        return businessService.getAllBusiness();
    }
    @PostMapping("/business")
    public ResponseEntity<Business> saveBusiness(@RequestBody Business Business) {
        return ResponseEntity.ok().body(businessService.saveBusiness(Business));
    }
    @GetMapping("/businessByNumber")
    public ResponseEntity<Business> getUserByUserNumber(@RequestParam Long businessNumber){
        return ResponseEntity.ok().body(businessService.getBusinessById(businessNumber).get());
    }
}
@Getter
@Setter
class createBusiness {
    private String businessName;
    private String location;
    private String businessPhoneNumber;
    private String userName;

}