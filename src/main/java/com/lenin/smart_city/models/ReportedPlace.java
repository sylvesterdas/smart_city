package com.lenin.smart_city.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lenin.smart_city.models.auth.User;
import com.lenin.smart_city.models.locations.Place;

@Entity
@Table(name="reported_places")
public class ReportedPlace {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public long id;
	
	@ManyToOne
	public Place place;
	
	@ManyToOne
	public User user;
	
	public String message;
	
}
