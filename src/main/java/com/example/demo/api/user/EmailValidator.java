package com.example.demo.api.user;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
@Service
public class EmailValidator implements Predicate<String>{
    @Override
    public boolean test(String sTring) {
        //TODO: Regex to validate email
        return true;
    }
}
