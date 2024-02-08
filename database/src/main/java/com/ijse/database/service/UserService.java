package com.ijse.database.service;


import com.ijse.database.dto.UserDto;
import com.ijse.database.dto.UserPasswordDto;
import com.ijse.database.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<UserEntity> getAllUsers();
    UserEntity createUser(UserDto userDto);
    UserEntity getUserById(Long id);
    UserEntity changePassword(Long id, UserPasswordDto userPasswordDto);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<UserEntity> getUserByUsername(String username);

}
