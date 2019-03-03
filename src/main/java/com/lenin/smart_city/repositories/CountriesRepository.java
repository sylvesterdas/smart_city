package com.lenin.smart_city.repositories;

import com.lenin.smart_city.models.locations.Country;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 */
public interface CountriesRepository extends JpaRepository<Country, Long> {

    
    
}