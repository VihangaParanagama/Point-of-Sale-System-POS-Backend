package com.ijse.database.dto;


import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private List<Item> products;
    private Long customerId;
    private Long userId;
}
