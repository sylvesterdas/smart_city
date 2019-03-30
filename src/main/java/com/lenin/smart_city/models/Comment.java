package com.lenin.smart_city.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lenin.smart_city.models.auth.User;
import com.lenin.smart_city.models.locations.Place;

@Entity
@Table(name="comments")
public class Comment {


	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	public long id;
	
	@Column(columnDefinition = "LONGTEXT")
	public String message;
	
	@ManyToOne
	public User user;
	
	@ManyToOne
	public Place place;
	
}
