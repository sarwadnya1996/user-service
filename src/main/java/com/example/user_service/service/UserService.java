package com.example.user_service.service;

import com.example.user_service.DtoEntityMapper;
import com.example.user_service.dto.UserDto;
import com.example.user_service.model.User;
import com.example.user_service.repository.AddressRepository;
import com.example.user_service.repository.ContactRepository;
import com.example.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private  AddressRepository addressRepository;
    private  ContactRepository contactRepository;
    private final DtoEntityMapper entityMapper;
    private  final PasswordEncoder passwordEncoder;



    public UserDto create(UserDto userDto) {
        User user = entityMapper.mapToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User createdUser =userRepository.save(user);
        return entityMapper.mapToUserDto(createdUser);
    }
}
