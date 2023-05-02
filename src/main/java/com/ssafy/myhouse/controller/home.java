package com.ssafy.myhouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class home {

    @GetMapping("/")
    public ResponseEntity<?> home(@AuthenticationPrincipal UserDetails member){
        if (member != null) {
            return new ResponseEntity<>(member.getUsername()+":"+member.getPassword(), HttpStatus.OK);
        }
        return new ResponseEntity<>("로그인 x", HttpStatus.OK);
    }
}
