package com.example.user_service;

import com.example.user_service.dto.AddressDto;
import com.example.user_service.dto.UserDto;
import com.example.user_service.model.Address;
import com.example.user_service.model.ContactDetails;
import com.example.user_service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DtoEntityMapper {



    public User mapToUser(UserDto userDto){
        User user= User.builder()
                .userName(userDto.getUserName())
                .fullName(userDto.getFullName())
                .emailId(userDto.getEmail())
                .password(userDto.getPassword())
                .isActive(true)
                .role("User").build();
        userDto.getAddress().stream().map(this::mapToAddress).forEach(user::addAddress);
        user.addContactDetail(ContactDetails.builder()
                .mobileNumber(userDto.getMobileNumber())
                .build());
        return user;
    }

    public  Address mapToAddress(AddressDto addressDto) {
        return Address.builder()
                .isDefaultShippingAddress(addressDto.getIsDefaultShippingAddress())
                .isDefaultBillingAddress(addressDto.getIsDefaultBillingAddress())
                .streetAddress(addressDto.getStreetAddress())
                .city(addressDto.getCity())
                .postalCode(addressDto.getPostalCode()).
                type(addressDto.getType())
                .build();
    }
    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .fullName(user.getFullName())
                .email(user.getEmailId())
                .password(user.getPassword())
                .mobileNumber(user.getContactDetails() != null
                        ? user.getContactDetails().getFirst().getMobileNumber()
                        : null)
                .address(user.getAddresses() != null && !user.getAddresses().isEmpty()
                        ? user.getAddresses().stream().map(this::mapToAddressDto).collect(Collectors.toList())
                        : null)
                .build();
    }

    public AddressDto mapToAddressDto(Address address) {
        return AddressDto.builder()
                .userId(address.getUser() != null ? address.getUser().getUserId() : 0L)
                .addressId(address.getAddressId())
                .streetAddress(address.getStreetAddress())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .isDefaultShippingAddress(address.getIsDefaultShippingAddress())
                .isDefaultBillingAddress(address.getIsDefaultBillingAddress())
                .type(address.getType())
                .build();
    }
}
