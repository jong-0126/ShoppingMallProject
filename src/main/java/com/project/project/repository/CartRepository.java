package com.project.project.repository;

import com.project.project.domain.entity.Cart;
import com.project.project.domain.entity.Item;
import com.project.project.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    List<Cart> findByUser(User user);
    Optional<Cart> findByUserAndItem(User user, Item item);
    void deleteById(UUID cart_key);
}
