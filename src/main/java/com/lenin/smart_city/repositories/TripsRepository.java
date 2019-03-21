package com.lenin.smart_city.repositories;

import com.lenin.smart_city.models.trips.Trip;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 */
public interface TripsRepository extends JpaRepository<Trip, Long> {
    
}