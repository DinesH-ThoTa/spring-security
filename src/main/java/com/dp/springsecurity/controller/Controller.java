package com.dp.springsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/emp")
    public ResponseEntity<String> getEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body("Employee");
    }

    @GetMapping("/pro")
    public ResponseEntity<String> getProduct(){
        return ResponseEntity.status(HttpStatus.OK).body("Employee");
    }

}
