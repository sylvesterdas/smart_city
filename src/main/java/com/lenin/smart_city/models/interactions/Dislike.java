package com.lenin.smart_city.models.interactions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="dislikes")
@Table(name="dislikes")
public class Dislike {

	@Id
	long id;
	
	@Column(name="post_id")
	long postId;
	
	@Column(name="user_id")
	long userId;
	
	
	
}
