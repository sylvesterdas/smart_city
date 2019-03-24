package com.lenin.smart_city.repositories;

import com.lenin.smart_city.models.auth.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {

	
	@Query(nativeQuery=true, value="SELECT * FROM users WHERE email LIKE ? LIMIT 1")
	User getOneByUsername(String user);
    
}