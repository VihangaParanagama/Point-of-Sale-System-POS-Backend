package com.ijse.database.controller;

import com.ijse.database.dto.LoginDto;
import com.ijse.database.dto.UserDto;
import com.ijse.database.repository.UserRepository;
import com.ijse.database.security.jwt.JwtUtils;
import com.ijse.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto user) {

        if(userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already in use");
        }

        if(userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already in use");
        }

        return ResponseEntity.ok(userService.createUser(user));

    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(jwt);

    }

    @GetMapping("/auth/check-existence")
    public ResponseEntity<Map<String, Boolean>> checkExistence(@RequestParam String username, @RequestParam String email) {
        System.out.println(username);
        Map<String, Boolean> existenceMap = new HashMap<>();
        existenceMap.put("usernameExists", userService.existsByUsername(username));
        existenceMap.put("emailExists", userService.existsByEmail(email));

        return ResponseEntity.ok().body(existenceMap);
    }

}