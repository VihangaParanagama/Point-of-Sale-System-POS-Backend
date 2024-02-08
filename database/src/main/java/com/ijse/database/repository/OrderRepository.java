package com.ijse.database.repository;

import com.ijse.database.entity.OrderEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    @Query("SELECT DISTINCT o.id, o.orderTime, c.name, o.total, u.fullName FROM OrderEntity o JOIN o.customerEntity c JOIN o.userEntity u WHERE (:startDate is null OR DATE(o.orderTime) BETWEEN :startDate AND :endDate) AND (:userId is null OR u.id = :userId)")
    List<Tuple> loadOrderDetails(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("userId") Long userId);
}
