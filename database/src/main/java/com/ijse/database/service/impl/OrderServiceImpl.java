package com.ijse.database.service.impl;

import com.ijse.database.dto.Item;
import com.ijse.database.dto.OrderDetails;
import com.ijse.database.dto.OrderDto;
import com.ijse.database.entity.OrderEntity;
import com.ijse.database.entity.OrderProductEntity;
import com.ijse.database.entity.ProductEntity;
import com.ijse.database.repository.CustomerRepository;
import com.ijse.database.repository.OrderRepository;
import com.ijse.database.repository.ProductRepository;
import com.ijse.database.repository.UserRepository;
import com.ijse.database.service.OrderService;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public OrderEntity createOrer(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        List<Item> products = orderDto.getProducts();

        if (products == null || products.isEmpty()) {
            return null;
        }

        List<OrderProductEntity> orderProductEntityList = new ArrayList<>();
        Double total = 0.0;

        for (Item item : products) {
            ProductEntity productEntity = productRepository.findById(item.getProductId()).orElse(null);

            if (productEntity != null && productEntity.getQty() >= item.getQuantity()) {
                OrderProductEntity orderProductEntity = new OrderProductEntity();
                orderProductEntity.setProductEntity(productEntity);
                orderProductEntity.setQty(item.getQuantity());
                orderProductEntity.setOrderEntity(orderEntity);

                orderProductEntityList.add(orderProductEntity);


                productEntity.setQty(productEntity.getQty() - item.getQuantity());
                productRepository.save(productEntity);


                total += productEntity.getPrice() * item.getQuantity();
            }
        }

        orderEntity.setTotal(total);
        orderEntity.setTax(total * 0.15);
        orderEntity.setOrderTime(LocalDateTime.now());
        orderEntity.setCustomerEntity(customerRepository.findById(orderDto.getCustomerId()).get());
        orderEntity.setOrderProductEntityList(orderProductEntityList);
        orderEntity.setUserEntity(userRepository.findById(orderDto.getUserId()).get());

        orderEntity = orderRepository.save(orderEntity);

        return orderEntity;
    }

    @Override
    public List<OrderDetails> loadOrderDetails(String date,Long userId) {

        LocalDate startDate =null;
        LocalDate endDate =null;
        if(date != null && !date.isEmpty()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            startDate = LocalDate.parse(date, formatter);
            endDate = startDate.plusDays(1);
        }


        List<Tuple> tuples = orderRepository.loadOrderDetails(startDate , endDate, userId);

        return mapToOrderDetailsList(tuples);

    }

    private List<OrderDetails> mapToOrderDetailsList(List<Tuple> result) {
        return result.stream()
                .map(this::mapToOrderDetails)
                .collect(Collectors.toList());
    }

    private OrderDetails mapToOrderDetails(Tuple row) {
        return new OrderDetails(
                row.get(0, Long.class),
                row.get(1, LocalDateTime.class),
                row.get(2, String.class),
                row.get(3, Double.class),
                row.get(4, String.class)
        );
    }

}
