package com.ijse.database.controller;

import com.ijse.database.dto.OrderDetails;
import com.ijse.database.dto.OrderDto;
import com.ijse.database.dto.OrderProductDto;
import com.ijse.database.entity.OrderEntity;
import com.ijse.database.service.OrderProductService;
import com.ijse.database.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProductService orderProductService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderEntity>> getAllOrders(){
        return ResponseEntity.status(200).body(orderService.getAllOrders());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long id){
        return ResponseEntity.status(200).body(orderService.getOrderById(id));
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderDto orderDto) {
        OrderEntity newOrder = orderService.createOrer(orderDto);
        return ResponseEntity.status(201).body(newOrder);
    }

    @GetMapping("/orders/details")
    public ResponseEntity<List<OrderDetails>> loadOrderDetails(@RequestParam(required = false) String date,@RequestParam(required = false) Long userId){
        return ResponseEntity.status(200).body(orderService.loadOrderDetails(date,userId));
    }

    @GetMapping("/orders/{orderId}/products")
    public ResponseEntity<List<OrderProductDto>> loadProductDetails(@PathVariable Long orderId){
        return ResponseEntity.status(200).body(orderProductService.loadOrderProductDetails(orderId));
    }

}
