package com.lenin.smart_city.repositories;

import com.lenin.smart_city.models.locations.Address;
import com.lenin.smart_city.models.locations.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * UserRepository
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
    
}