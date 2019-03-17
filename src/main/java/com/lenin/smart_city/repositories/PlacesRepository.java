package com.lenin.smart_city.repositories;

import com.lenin.smart_city.models.locations.Place;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 */
public interface PlacesRepository extends JpaRepository<Place, Long> {
    
}