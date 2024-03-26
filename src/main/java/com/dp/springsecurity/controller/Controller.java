package com.dp.springsecurity.controller;

import com.dp.springsecurity.entity.UserInfo;
import com.dp.springsecurity.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserInfoRepo userInfoRepo;

    @GetMapping("/products/emp")
    public ResponseEntity<String> getEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body("Employee");
    }

    @GetMapping("/products/get")
    public ResponseEntity<String> getProduct(){
        return ResponseEntity.status(HttpStatus.OK).body("Employee");
    }

    @PostMapping("/products/new")
    public ResponseEntity<String> createUser(@RequestBody UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepo.save(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
    }

}
