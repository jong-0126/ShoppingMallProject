package com.project.project.repository;

import com.project.project.domain.entity.Orders;
import com.project.project.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
    List<Orders> findByUser(User user);
}
