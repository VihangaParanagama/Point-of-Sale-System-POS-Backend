package com.ijse.database.service;

import com.ijse.database.dto.OrderDetails;
import com.ijse.database.dto.OrderDto;
import com.ijse.database.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<OrderEntity> getAllOrders();
    OrderEntity getOrderById(Long id);
    OrderEntity createOrer(OrderDto orderDto);
    List<OrderDetails> loadOrderDetails(String date,Long userId);
}
