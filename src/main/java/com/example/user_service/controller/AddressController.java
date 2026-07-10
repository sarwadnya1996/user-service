package com.example.user_service.controller;

import com.example.user_service.dto.AddressDto;
import com.example.user_service.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/adddress")
public class AddressController {
    private final AddressService addressService;
    @PostMapping("/addAddress")
    private ResponseEntity<AddressDto> addAddress(@RequestBody AddressDto addressDto){
        log.info("adding address for user {}",addressDto.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(CompletableFuture.supplyAsync(()->addressService.addAddress(addressDto)).join());

    }

}
