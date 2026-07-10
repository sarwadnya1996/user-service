package com.example.user_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long userId;
    @NotNull
    private String userName;
    @NotNull
    private String fullName;
    private String email;
    @JsonIgnore
    private String password;
    private List<AddressDto> address;
    @NotNull
    private String mobileNumber;


}
