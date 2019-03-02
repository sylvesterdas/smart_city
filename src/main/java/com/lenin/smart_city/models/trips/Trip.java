package com.lenin.smart_city.models.trips;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="trips")
@Table(name="trips")
public class Trip {

	@Id
	public long id;
	
	@Column(name="start_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date startDate;

	@Column(name="end_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date endDate;
	
	@Column(name="type")
	public TripType type;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public Set<Booking> bookings = new HashSet<>();
}
