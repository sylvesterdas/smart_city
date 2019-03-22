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
    
    @Query(nativeQuery=true, value="SELECT * FROM `places` as p join addresses as a ON p.address_id = a.id join cities as c ON city_id = c.id where title LIKE ? and name LIKE ?  order by p.id desc")
    List<Place> getByNameAndCity(String name, String city);
    
    @Query(nativeQuery=true, value="SELECT * FROM `places` as p join addresses as a ON p.address_id = a.id join cities as c ON city_id = c.id where name LIKE ?  order by p.id desc")
    List<Place> getByCity(String city);
    
}