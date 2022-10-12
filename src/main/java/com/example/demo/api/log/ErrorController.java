package com.example.demo.api.log;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
@Slf4j
public class ErrorController {
    @PostMapping("/log")
    public void GetErrorLog(@RequestBody error error){
        System.out.println(error);
    }
}
@Getter
@Setter
 class error{
    String errorName="";
    String errorData="";
}