package com.lenin.smart_city.repositories;

import com.lenin.smart_city.models.locations.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * UserRepository
 */
public interface CitiesRepository extends JpaRepository<City, Long> {

    String checkExistanceQuery = "SELECT count(*) FROM states s JOIN cities c ON c.state_id = s.id WHERE s.name=? AND c.name=?";

    @Query(nativeQuery=true, value=checkExistanceQuery)
    int checkExistance(String state, String city);
    
}