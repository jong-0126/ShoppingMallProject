package com.project.project.repository;

import com.project.project.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    @Override
    Optional<Item> findById(UUID uuid);

    @Query(value = "SELECT * FROM item ORDER BY RAND() LIMIT 2", nativeQuery = true)
    List<Item> findRandomItems();
}
