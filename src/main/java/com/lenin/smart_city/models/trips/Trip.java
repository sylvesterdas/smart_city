package com.lenin.smart_city.models.trips;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity(name="trips")
@Table(name="trips", uniqueConstraints=@UniqueConstraint(columnNames= {"name"}))
public class Trip {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	public long id;
	
	public String name;
	
	@Column(name="start_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date startDate;

	@Column(name="end_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date endDate;
	
	@Column(name="type")
	public TripType type;
	
	@OneToMany(mappedBy="trip")
	public Set<Itenary> itenaries;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public Set<Booking> bookings = new HashSet<>();
}
