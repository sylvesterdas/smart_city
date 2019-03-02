package com.lenin.smart_city.models.trips;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lenin.smart_city.models.auth.User;

@Entity(name="bookings")
@Table(name="bookings")
public class Booking {

	@Id
	public long id;
	
	@ManyToOne
	public Trip trip;
	
	@ManyToOne
	public User user;
	
	@Column(name="is_cancelled")
	public boolean isCancelled;
	
}
