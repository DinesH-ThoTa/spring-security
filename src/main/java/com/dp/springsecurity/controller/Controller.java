package com.dp.springsecurity.controller;

import com.dp.springsecurity.entity.AuthRequest;
import com.dp.springsecurity.entity.UserInfo;
import com.dp.springsecurity.repository.UserInfoRepo;
import com.dp.springsecurity.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


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
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUserName());
        }
        throw new IllegalArgumentException("User not found");
    }

}
