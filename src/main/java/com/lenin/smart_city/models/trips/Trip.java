package com.lenin.smart_city.models.trips;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="trips")
@Table(name="trips")
public class Trip {

	@Id
	long id;
	
	@Column(name="start_date")
	Date startDate;

	@Column(name="end_date")
	Date endDate;
	
	@Column(name="type")
	TripType type;
	
}
