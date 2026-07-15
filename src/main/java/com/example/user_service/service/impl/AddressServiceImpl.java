package com.example.user_service.service.impl;

import com.example.user_service.DtoEntityMapper;
import com.example.user_service.dto.AddressDto;
import com.example.user_service.exception.UserSystemException;
import com.example.user_service.model.Address;
import com.example.user_service.model.User;
import com.example.user_service.repository.AddressRepository;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.service.AddressService;
import jakarta.transaction.SystemException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<AddressDto> retrieveAddresses() {
        List<Address> allAddresses= addressRepository.findAll();
        return allAddresses.stream().map(mapper::mapToAddressDto).toList();
    }

    @Override
    public AddressDto update(AddressDto addressDto) {
        Optional<User> existing = userRepository.findUserByUserId(addressDto.getUserId());
        if(existing.isEmpty()){
            throw new UserSystemException("User does not exist",HttpStatus.NOT_FOUND);
        }
        Address updateAddress= mapper.mapToAddress(addressDto);

        updateAddress.setUser(existing.get());
        mapper.setAuditFields(updateAddress);
       return  mapper.mapToAddressDto(addressRepository.save(updateAddress));

    }

    @Override
    public void delete(Long addressId) {
        Optional<Address> optionalAddress =addressRepository.findById(addressId);
        Address existing =optionalAddress.orElseThrow(()->new UserSystemException("Invalid Address",HttpStatus.BAD_GATEWAY));
        existing.setIsActive(false);
        addressRepository.save(existing);

    }
}
