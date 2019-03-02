package com.lenin.smart_city.models.trips;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lenin.smart_city.models.locations.Place;

@Entity(name="itenaries")
@Table(name="itenaries")
public class Itenary {

	@Id
	public long id;
	
	@ManyToOne
	public Trip trip;
	
	@ManyToOne
	public Place place;
	
	@Column(name="start_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date startTime;
	
	@Column(name="end_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date endTime;
	
}
