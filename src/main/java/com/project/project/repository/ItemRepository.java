package com.project.project.repository;

import com.project.project.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    @Override
    Optional<Item> findById(UUID uuid);
}
