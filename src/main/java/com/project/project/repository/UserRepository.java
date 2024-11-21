package com.project.project.repository;

import com.project.project.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID user_id);
    @Query(value = "SELECT * FROM User WHERE is_super_admin = false;", nativeQuery = true)
    List<User> findAllUsers();
    void deleteById(UUID user_id);
}
