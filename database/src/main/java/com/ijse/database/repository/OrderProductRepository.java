package com.ijse.database.repository;

import com.ijse.database.entity.OrderProductEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProductEntity,Long> {

    @Query("SELECT p.name, op.qty, p.price FROM OrderEntity o JOIN o.orderProductEntityList op JOIN op.productEntity p WHERE o.id = :orderId")
    List<Tuple> getProductDetailsForOrder(@Param("orderId") Long orderId);


}
