package com.example.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.petshop.service.UserService;

@RestController
public class TestController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/verify")
    public void verify(@Param("code") String code){
        userService.verify(code);
    }

}
