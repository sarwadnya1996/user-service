package com.example.user_service.controller;

import com.example.commons.annotation.Authenticate;
import com.example.user_service.dto.AddressDto;
import com.example.user_service.service.AddressService;
import com.example.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;
    private final UserService userService;

    @PostMapping("/addAddress")
    @Authenticate
    private ResponseEntity<AddressDto> addAddress(@RequestBody AddressDto addressDto){
        log.info("adding address for user {}",addressDto.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(CompletableFuture.supplyAsync(()->addressService.addAddress(addressDto)).join());

    }
    @GetMapping("/getAllAddresses")
    @Authenticate
    public List<AddressDto> getAllAddresses(){
        return CompletableFuture.supplyAsync(addressService::retrieveAddresses).join();
    }
    @PatchMapping("/updateAddress")
    @Authenticate
    public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto){
        log.info("updating address for userId {} ",addressDto.getUserId());
        return ResponseEntity.ok(addressService.update(addressDto));
    }

}
