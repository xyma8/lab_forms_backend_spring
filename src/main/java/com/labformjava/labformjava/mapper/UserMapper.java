package com.labformjava.labformjava.mapper;

import com.labformjava.labformjava.dto.UserDto;
import com.labformjava.labformjava.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getLogin(),
                user.getPassword(),
                user.getGender(),
                user.getDarktheme(),
                user.getToken()
        );
    }

    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getEmail(),
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getGender(),
                userDto.getDarktheme(),
                userDto.getToken()
        );
    }
}
