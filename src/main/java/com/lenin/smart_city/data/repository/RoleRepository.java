package com.lenin.smart_city.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lenin.smart_city.models.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	
	
}
