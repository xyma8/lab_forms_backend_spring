package com.labformjava.labformjava.service;

import com.labformjava.labformjava.dto.LoginRequestDto;
import com.labformjava.labformjava.dto.UserDataDto;
import com.labformjava.labformjava.dto.UserDto;
import com.labformjava.labformjava.dto.UserTokenDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long userId, UserDto userDto);

    String loginUser(LoginRequestDto loginRequestDto);

    UserDataDto getUserDataByToken(UserTokenDto userTokenDto);
}
