package com.ijse.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    private String mobile;
    private String address;
    private String fullName;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<OrderEntity> orderEntityList;
}
