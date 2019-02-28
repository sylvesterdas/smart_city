package com.lenin.smart_city.models.locations;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="timings")
@Table(name="timings")
public class Timing {

	@Id
	long id;

	@Column(name="start_time")
	Timestamp startTime;
	
	@Column(name="end_time")
	Timestamp endTime;
	
	@Column(name="place_id")
	long placeId;
	
}
