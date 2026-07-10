package com.example.user_service.controller;

import com.example.commons.annotation.Authenticate;
import com.example.user_service.dto.UserDto;
import com.example.user_service.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
        log.info("creating user");
        return ResponseEntity.status(201).body(CompletableFuture.supplyAsync(() -> userService.create(userDto)).join());
    }
    @Authenticate
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        log.info("getting all user info");
        return ResponseEntity.ok(CompletableFuture.supplyAsync(()->userService.getAllUsers()).join());

    }

}
