package com.lenin.smart_city.repositories;

import com.lenin.smart_city.models.locations.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * UserRepository
 */
public interface PlaceRepository extends JpaRepository<City, Long> {

    String checkExistanceQuery = "SELECT count(*) FROM places s WHERE s.title=? ";

    @Query(nativeQuery=true, value=checkExistanceQuery)
    int checkExistance(String state);
    
}