package com.example.tdddemo.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingsService {
    public String getGreetingsByGender(String gender) {
        if("male".equals(gender)){
            return "Mr.";
        }
        else if("female".equals(gender)){
            return "Mrs.";
        }
        throw new RuntimeException("Gender not recognised");
    }
}
