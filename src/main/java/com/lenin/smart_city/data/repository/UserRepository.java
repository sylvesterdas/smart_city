package com.lenin.smart_city.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.lenin.smart_city.models.auth.User;

public interface UserRepository extends CrudRepository<User, Long> {

	
	
}
