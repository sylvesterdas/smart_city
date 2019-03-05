package com.lenin.smart_city.repositories;

import com.lenin.smart_city.models.locations.State;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * UserRepository
 */
public interface StatesRepository extends JpaRepository<State, Long> {

    String checkExistanceQuery = "SELECT count(*) FROM states s JOIN countries c ON c.id = s.country_id WHERE s.name=? AND c.name=?";

    @Query(nativeQuery=true, value=checkExistanceQuery)
    int checkExistance(String state, String country);
    
}