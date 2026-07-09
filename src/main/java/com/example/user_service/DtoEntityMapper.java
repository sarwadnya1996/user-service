package com.example.user_service;

import com.example.user_service.dto.AddressDto;
import com.example.user_service.dto.UserDto;
import com.example.user_service.model.Address;
import com.example.user_service.model.ContactDetails;
import com.example.user_service.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DtoEntityMapper {
    private  final ModelMapper modelMapper;

    public User mapToUser(UserDto userDto){
        User user= User.builder()
                .userName(userDto.getUserName())
                .fullName(userDto.getFullName())
                .emailId(userDto.getEmail())
                .password(userDto.getPassword())
                .isActive(true)
                .role("User").build();
        user.addAddress(mapToAddress(userDto.getAddress()));
        user.addContactDetail(ContactDetails.builder()
                .mobileNumber(userDto.getMobileNumber())
                .build());
        return user;
    }

    private Address mapToAddress(AddressDto addressDto) {
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
                .userId(user.getUser_id())
                .userName(user.getUserName())
                .fullName(user.getFullName())
                .email(user.getEmailId())
                .password(user.getPassword())
                .mobileNumber(user.getContactDetails() != null
                        ? user.getContactDetails().getFirst().getMobileNumber()
                        : null)
                .address(user.getAddresses() != null && !user.getAddresses().isEmpty()
                        ? mapToAddressDto(user.getAddresses().get(0))
                        : null)
                .build();
    }

    private AddressDto mapToAddressDto(Address address) {
        return AddressDto.builder()
                .userId(address.getUser() != null ? address.getUser().getUser_id() : 0L)
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
