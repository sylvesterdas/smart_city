package com.lenin.smart_city.repositories;

import com.lenin.smart_city.models.trips.Itenary;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 */
public interface ItenariesRepository extends JpaRepository<Itenary, Long> {
    
}