package com.example.user_service.controller;

import com.example.commons.annotation.Authenticate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api")
public class UserController {
    @GetMapping("/t")
    @Authenticate
    public ResponseEntity<String> getEnv(){
        return ResponseEntity.ok("/t");
    }
}
