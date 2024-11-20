package com.project.project.repository;

import com.project.project.domain.entity.Orders;
import com.project.project.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {

    @Query("SELECT o FROM Orders o JOIN FETCH o.orderItems oi JOIN FETCH oi.item WHERE o.order_key = :orderKey")
    Optional<Orders> findOrderWithItems(@Param("orderKey") UUID orderKey);

    List<Orders> findByUser(User user);
    Optional<Orders> findById(UUID orders_key);

}
