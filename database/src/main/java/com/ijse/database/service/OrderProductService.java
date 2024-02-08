package com.ijse.database.service;

import com.ijse.database.dto.OrderProductDto;

import java.util.List;

public interface OrderProductService {
    List<OrderProductDto> loadOrderProductDetails(Long orderId);
}
