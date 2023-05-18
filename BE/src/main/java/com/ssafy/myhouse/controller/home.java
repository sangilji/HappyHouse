package com.ssafy.myhouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class home {

    @GetMapping("/")
    public ResponseEntity<?> home(){

        return new ResponseEntity<>("home", HttpStatus.OK);
    }
}
