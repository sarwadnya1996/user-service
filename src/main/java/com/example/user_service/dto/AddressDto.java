package com.example.user_service.dto;

import com.example.user_service.model.AddressType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userId;
    private Long addressId;
    @NotNull
    private String streetAddress;
    @NotNull
    private String city;
    @NotNull
    private String postalCode;
    private Boolean isDefaultShippingAddress;
    private Boolean isDefaultBillingAddress;
    @NotNull
    private AddressType type;
    @Builder.Default
    private Boolean isActive = true;
}
