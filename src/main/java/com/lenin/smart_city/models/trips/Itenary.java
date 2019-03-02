package com.lenin.smart_city.models.trips;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="itenaries")
@Table(name="itenaries")
public class Itenary {

	@Id
	long id;
	
	@Column(name="trip_id")
	long tripId;
	
	@Column(name="place_id")
	long placeId;
	
	@Column(name="start_time")
	Timestamp startTime;
	
	@Column(name="end_time")
	Timestamp endTime;
	
}
