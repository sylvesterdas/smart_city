package com.lenin.smart_city.repositories;

import com.lenin.smart_city.models.locations.Address;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
    
}