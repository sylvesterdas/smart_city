package com.lenin.smart_city.models.locations;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="timings")
@Table(name="timings")
public class Timing {

	@Id
	public long id;

	@Column(name="start_time")
	public Timestamp startTime;
	
	@Column(name="end_time")
	public Timestamp endTime;
	
	@ManyToOne
	public Place place;
	
}
