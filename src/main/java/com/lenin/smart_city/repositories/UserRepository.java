package com.lenin.smart_city.repositories;

import com.lenin.smart_city.models.auth.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {

    
}