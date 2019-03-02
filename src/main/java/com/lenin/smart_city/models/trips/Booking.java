package com.lenin.smart_city.models.trips;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="bookings")
@Table(name="bookings")
public class Booking {

	@Id
	long id;
	
	@Column(name="trip_id")
	long tripId;
	
	@Column(name="user_id")
	long userId;
	
	@Column(name="is_cancelled")
	boolean isCancelled;
	
}
