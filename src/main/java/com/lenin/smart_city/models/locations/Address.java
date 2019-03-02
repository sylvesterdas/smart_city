package com.lenin.smart_city.models.locations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="addresses")
@Table(name="addresses")
public class Address {

	@Id
	public long id;
	
	@Column(name="line_1")
	public String line1;
	
	@Column(name="line_2")
	public String line2;
	
	public String landmark;
	
	@ManyToOne
	public City city;
	
	public double latitude;
	
	public double longitude;
	
	
}
