package com.lenin.smart_city.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lenin.smart_city.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	
	
}
