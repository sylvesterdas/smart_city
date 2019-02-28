package com.lenin.smart_city.models.interactions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="ratings")
@Table(name="ratings")
public class Rating {

	@Id
	long id;
	
	@Column(name="post_id")
	long postId;
	
	@Column(name="user_id")
	long userId;
	
	short rating;
}
