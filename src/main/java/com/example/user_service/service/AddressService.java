package com.example.user_service.service;

import com.example.user_service.dto.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto addAddress(AddressDto addressDto);

    List<AddressDto> retrieveAddresses();

    AddressDto update(AddressDto addressDto);

    void delete(Long addressId);
}
