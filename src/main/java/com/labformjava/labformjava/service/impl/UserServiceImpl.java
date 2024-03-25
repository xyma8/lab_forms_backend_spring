package com.labformjava.labformjava.service.impl;

import com.labformjava.labformjava.dto.LoginRequestDto;
import com.labformjava.labformjava.dto.UserDto;
import com.labformjava.labformjava.entity.User;
import com.labformjava.labformjava.exception.ResourceNotFoundException;
import com.labformjava.labformjava.exception.UnauthorizedException;
import com.labformjava.labformjava.mapper.UserMapper;
import com.labformjava.labformjava.repository.UserRepository;
import com.labformjava.labformjava.service.UserService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final int TOKEN_LENGTH = 32;
    private final UserRepository userRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private String generateToken(String login) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[TOKEN_LENGTH];
        secureRandom.nextBytes(tokenBytes);
        BigInteger tokenNumber = new BigInteger(1, tokenBytes);
        String token = tokenNumber.toString(16);// Конвертируем в шестнадцатеричную строку
        return token + ":" + login;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setToken(generateToken(user.getLogin()));
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User is not exists with id = " + userId));

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUserDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User is not exists with id = " + userId));

        user.setName(updatedUserDto.getName());
        user.setSurname(updatedUserDto.getSurname());

        User updatedUserObj = userRepository.save(user);
        return UserMapper.mapToUserDto(updatedUserObj);
    }

    @Override
    public String loginUser(LoginRequestDto lrDto) {
        System.out.println(lrDto.getLogin());
        String hashPassword = userRepository.hashPassword(lrDto.getLogin());
        boolean match = passwordEncoder.matches(lrDto.getPassword(), hashPassword);
        if(!match) {
            new UnauthorizedException("Password incorrect");
        }
        return "token";
    }
}
