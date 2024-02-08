package com.ijse.database.controller;

import com.ijse.database.dto.UserDto;
import com.ijse.database.dto.UserPasswordDto;
import com.ijse.database.entity.UserEntity;
import com.ijse.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public UserEntity createUser(@RequestBody UserDto userDto){
        System.out.println(userDto);
        return userService.createUser(userDto);
    }

    @PostMapping("/users/{id}/change-password")
    public ResponseEntity <UserEntity> changeUserPassword(@PathVariable Long id,@RequestBody UserPasswordDto userPasswordDto) {
        return ResponseEntity.ok().body(userService.changePassword(id, userPasswordDto));
    }

    @GetMapping("/users/{username}")
    public Optional<UserEntity> getNameByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping("/user/{userId}")
    public UserEntity getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }
}
