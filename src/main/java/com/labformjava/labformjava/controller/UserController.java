package com.labformjava.labformjava.controller;

import com.labformjava.labformjava.dto.*;
import com.labformjava.labformjava.entity.User;
import com.labformjava.labformjava.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenDto> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        String token = userService.loginUser(loginRequestDto);
        UserTokenDto userTokenDto = new UserTokenDto();
        userTokenDto.setToken(token);
        return ResponseEntity.ok(userTokenDto);
    }

    @GetMapping("/data")
    public ResponseEntity<UserDataDto> getUserDataByToken(@RequestHeader("TokenAuth") String token) {
        UserDto userDto = userService.getUserDataByToken(token);
        UserDataDto userDataDto = new UserDataDto();
        userDataDto.setLogin(userDto.getLogin());
        userDataDto.setName(userDto.getName());
        return ResponseEntity.ok(userDataDto);
    }

    @GetMapping("/theme")
    public ResponseEntity<UserThemeDto> getUserTheme(@RequestHeader("TokenAuth") String token) {
        UserDto userDto = userService.getUserDataByToken(token);
        int theme = userDto.getDarktheme();
        UserThemeDto userThemeDto = new UserThemeDto();
        userThemeDto.setTheme(theme);
        return ResponseEntity.ok(userThemeDto);
    }

    @PostMapping("/theme/change")
    public ResponseEntity<UserThemeDto> setUserTheme(@RequestBody UserTokenDto userTokenDto) {
        UserDto userDto = userService.changeTheme(userTokenDto.getToken());
        int theme = userDto.getDarktheme();
        UserThemeDto userThemeDto = new UserThemeDto();
        userThemeDto.setTheme(theme);
        return ResponseEntity.ok(userThemeDto);
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
