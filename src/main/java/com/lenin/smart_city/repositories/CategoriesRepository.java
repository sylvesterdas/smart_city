package com.lenin.smart_city.repositories;

import com.lenin.smart_city.models.locations.Category;
import com.lenin.smart_city.models.locations.City;
import com.lenin.smart_city.models.trips.Itenary;
import com.lenin.smart_city.models.trips.Trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * UserRepository
 */
public interface CategoriesRepository extends JpaRepository<Category, Long> {
    
}