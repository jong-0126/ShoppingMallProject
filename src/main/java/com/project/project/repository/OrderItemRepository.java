package com.project.project.repository;

import com.project.project.domain.entity.OrderItem;
import com.project.project.domain.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    List<OrderItem> findByOrders(Orders orders);
    @Query("SELECT oi FROM OrderItem oi WHERE oi.orders.order_key = :orderKey")
    List<OrderItem> findByOrderKey(@Param("orderKey") UUID orderKey);

}
