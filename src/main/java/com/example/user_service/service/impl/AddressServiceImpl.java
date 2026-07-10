package com.example.user_service.service.impl;

import com.example.user_service.DtoEntityMapper;
import com.example.user_service.dto.AddressDto;
import com.example.user_service.exception.UserSystemException;
import com.example.user_service.model.Address;
import com.example.user_service.model.User;
import com.example.user_service.repository.AddressRepository;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final DtoEntityMapper mapper;
    @Override
    public AddressDto addAddress(AddressDto addressDto) {
        User existing =  userRepository.findUserByUserId(addressDto.getUserId()).orElseThrow( ()->new UserSystemException("User does not exists", HttpStatus.NOT_FOUND));
        Address address =  mapper.mapToAddress(addressDto);
        address.setUser(existing);
        Address newAddress=addressRepository.save(address);
        return mapper.mapToAddressDto(newAddress);
    }
}
