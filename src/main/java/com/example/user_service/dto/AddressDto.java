package com.example.user_service.dto;

import com.example.user_service.model.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {
    private Long userId;
    private Long addressId;
    private String streetAddress;
    private String city;
    private String postalCode;
    private Boolean  isDefaultShippingAddress;
    private Boolean isDefaultBillingAddress;
    private AddressType type;
}
