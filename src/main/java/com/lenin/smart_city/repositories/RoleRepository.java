package com.lenin.smart_city.repositories;

import com.lenin.smart_city.models.auth.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * RoleRepository
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    String findByUserEmailQuery = "select r.* from users u inner join roles r on u.role_id=r.id where u.email=?";
    String checkAdminQuery = "select count(*) from users as u join roles as r on u.role_id = r.id where r.id < 3 and u.email=?";

    @Query(nativeQuery=true, value=findByUserEmailQuery)
    Role findByUserEmail(String email);

    @Query(nativeQuery=true, value=checkAdminQuery)
    int checkAdmin(String email);
    
}