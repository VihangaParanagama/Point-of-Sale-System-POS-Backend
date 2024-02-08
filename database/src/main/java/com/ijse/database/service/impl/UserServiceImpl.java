package com.ijse.database.service.impl;


import com.ijse.database.dto.UserDto;
import com.ijse.database.dto.UserPasswordDto;
import com.ijse.database.entity.UserEntity;
import com.ijse.database.repository.UserRepository;
import com.ijse.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity createUser(UserDto userDto) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setAddress(userDto.getAddress());
        newUser.setFullName(userDto.getFullName());
        newUser.setMobile(userDto.getMobile());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntity changePassword(Long id, UserPasswordDto userPasswordDto) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);

        if(userEntity != null){
            userEntity.setPassword(userPasswordDto.getPassword());
            return userRepository.save(userEntity);
        }else{
            return null;
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<UserEntity> getUserByUsername(String username) {
        System.out.println("Service" + username);
        return userRepository.findByUsername(username);
    }
}
