package com.example.demo.api.Courier;

import com.example.demo.api.Business.Business;
import com.example.demo.api.Business.BusinessService;
import com.example.demo.api.user.AppUser;
import com.example.demo.api.user.Role;
import com.example.demo.api.user.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
@Slf4j
public class CourierController {

    private final CourierService courierService;
    private final UserService userService;
    private final BusinessService businessService;
    @GetMapping("/courier")
    public List<courier> GetAllCouriers(){
        return courierService.getAllCouriers();
    }
    @GetMapping("/getCourier")
    public ResponseEntity<courier>getCourier(@RequestParam String userName){
        System.out.println("Blop");
        return ResponseEntity.ok().body(courierService.getCourier(userService.getUser(userName).orElseThrow()).orElseThrow());
    }
    @PostMapping("/courier")
    public ResponseEntity<courier> saveCourier(@RequestParam String userName,@RequestParam String businessName) {
        System.out.println(userName);
        AppUser user= userService.getUser(userName).orElseThrow();
        courier courier= new courier(user);
        userService.addRoleToUser(userName, Role.COURIER);
        Business business= businessService.getBusiness(businessName).orElseThrow();
        userService.addBusinessToUser(userName,business.getId());
        return ResponseEntity.ok().body(courierService.saveCourier(courier));
    }
}
