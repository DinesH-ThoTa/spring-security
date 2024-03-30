package com.dp.springsecurity.controller;

import com.dp.springsecurity.entity.AuthRequest;
import com.dp.springsecurity.entity.UserInfo;
import com.dp.springsecurity.repository.UserInfoRepo;
import com.dp.springsecurity.service.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    private JwtToken jwtToken;


    @GetMapping("/products/emp")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> getEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body("Employee");
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/products/get")
    public ResponseEntity<String> getProduct(){
        return ResponseEntity.status(HttpStatus.OK).body("Product");
    }

    @PostMapping("/products/new")
    public ResponseEntity<String> createUser(@RequestBody UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepo.save(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){

        return jwtToken.generateToken(authRequest.getUserName());
    }

}
