package com.lenin.smart_city.models.interactions;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lenin.smart_city.models.auth.User;
import com.lenin.smart_city.models.trips.Post;

@Entity(name="ratings")
@Table(name="ratings")
public class Rating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3779906955457433364L;

	@Id
	public long id;
	
	@ManyToOne
	public Post post;
	
	@ManyToOne
	public User user;
	
	@Column(length=1)
	short rating;
}
