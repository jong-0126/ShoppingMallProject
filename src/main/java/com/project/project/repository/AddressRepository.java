package com.project.project.repository;


import com.project.project.domain.entity.Address;
import com.project.project.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID>{
    List<Address> findByUser(User user);
    Optional<Address> findById(UUID address);
}
