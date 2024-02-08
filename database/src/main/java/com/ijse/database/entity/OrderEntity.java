package com.ijse.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime orderTime;

    @Column(nullable = false)
    private Double total;

    private Double tax;


    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    List<OrderProductEntity> orderProductEntityList;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}
