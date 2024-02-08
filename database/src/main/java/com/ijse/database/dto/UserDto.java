package com.ijse.database.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;
    private String email;
    private String password;
    private String mobile;
    private String address;
    private String fullName;
}
