package com.example.demo.api.Courier;

import com.example.demo.api.user.AppUser;
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
    @GetMapping("/courier")
    public List<courier> GetAllCouriers(){
        return courierService.getAllCouriers();
    }

    @PostMapping("/courier")
    public ResponseEntity<courier> saveCourier(@RequestBody String UserName) {
        AppUser user= userService.getUser(UserName).orElseThrow();
        courier courier= new courier(user);
        return ResponseEntity.ok().body(courierService.saveCourier(courier));
    }
}
