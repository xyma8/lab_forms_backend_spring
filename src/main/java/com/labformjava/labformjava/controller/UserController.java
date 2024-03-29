package com.labformjava.labformjava.controller;

import com.labformjava.labformjava.dto.LoginRequestDto;
import com.labformjava.labformjava.dto.UserDataDto;
import com.labformjava.labformjava.dto.UserDto;
import com.labformjava.labformjava.dto.UserTokenDto;
import com.labformjava.labformjava.entity.User;
import com.labformjava.labformjava.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Build Add User REST API
    @PostMapping("/signup")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        String token = userService.loginUser(loginRequestDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/data")
    public ResponseEntity<UserDataDto> getUserDataByToken(@RequestBody UserTokenDto userTokenDto) {
        UserDto userDto = userService.getUserDataByToken(userTokenDto);
        UserDataDto userDataDto = new UserDataDto();
        userDataDto.setLogin(userDto.getLogin());
        userDataDto.setName(userDto.getName());
        return ResponseEntity.ok(userDataDto);
    }

    @GetMapping("/token")
    public ResponseEntity<Integer> getUserTheme(@RequestHeader("TokenAuth") String token) {
        int theme = userService.getUserTheme(token);
        return ResponseEntity.ok(theme);
    }

    @PostMapping("/token/set")
    public ResponseEntity<Integer> setUserTheme() {
        return null;
    }

    //Build Get User REST API
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    //Build Get All Users REST API
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users =  userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //Build Update User REST API
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto updatedUser) {
        UserDto userDto = userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(userDto);
    }
}
