package com.lenin.smart_city.repositories;

import com.lenin.smart_city.models.locations.Place;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * UserRepository
 */
public interface PlaceRepository extends JpaRepository<Place, Long> {

    String checkExistanceQuery = "SELECT count(*) FROM places s WHERE s.title=? order by id desc";

    @Query(nativeQuery=true, value=checkExistanceQuery)
    int checkExistance(String state);
    
    @Query(nativeQuery=true, value="SELECT * FROM places WHERE title LIKE ? order by id desc")
    List<Place> getByName(String name);
    
}