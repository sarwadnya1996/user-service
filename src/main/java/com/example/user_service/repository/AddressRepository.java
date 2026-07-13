package com.example.user_service.repository;

import com.example.user_service.model.Address;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @NotNull
    List<Address> findAll();
}
