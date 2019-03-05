package com.lenin.smart_city.repositories;

import com.lenin.smart_city.models.locations.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * RoleRepository
 */
public interface CategoriesRepository extends JpaRepository<Category, Long> {

	String checkExistanceQuery = "SELECT count(*) FROM categories as s WHERE s.name=?";

    @Query(nativeQuery=true, value=checkExistanceQuery)
    int checkExistance(String category);
    
}