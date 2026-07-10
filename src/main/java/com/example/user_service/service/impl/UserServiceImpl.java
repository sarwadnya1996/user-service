package com.example.user_service.service.impl;

import com.example.user_service.DtoEntityMapper;
import com.example.user_service.dto.UserDto;
import com.example.user_service.exception.UserSystemException;
import com.example.user_service.model.User;
import com.example.user_service.repository.AddressRepository;
import com.example.user_service.repository.ContactRepository;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DtoEntityMapper entityMapper;
    private final PasswordEncoder passwordEncoder;


    public UserDto create(UserDto userDto) {
        Optional<User> existing = userRepository.findUserByUserName(userDto.getUserName());
        if (existing.isPresent()) {
            throw new UserSystemException("UserName already exists", HttpStatus.CONFLICT);
        }
        User user = entityMapper.mapToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User createdUser = userRepository.save(user);
        return entityMapper.mapToUserDto(createdUser);
    }

    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream().map(entityMapper::mapToUserDto).collect(Collectors.toList());
    }
}
