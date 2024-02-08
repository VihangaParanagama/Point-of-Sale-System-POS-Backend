package com.ijse.database.service.impl;

import com.ijse.database.dto.OrderProductDto;
import com.ijse.database.repository.OrderProductRepository;
import com.ijse.database.service.OrderProductService;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;
    @Override
    public List<OrderProductDto> loadOrderProductDetails(Long orderId) {
        List<Tuple> tuples = orderProductRepository.getProductDetailsForOrder(orderId);

        return mapToProductDetailsList(tuples);
    }

    private List<OrderProductDto> mapToProductDetailsList(List<Tuple> result) {
        return result.stream()
                .map(this::mapToproductDetails)
                .collect(Collectors.toList());
    }

    private OrderProductDto mapToproductDetails(Tuple row) {
        return new OrderProductDto(
                row.get(0, String.class),
                row.get(1, Integer.class),
                row.get(2, Double.class)
        );
    }
}
