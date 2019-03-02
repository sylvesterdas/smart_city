package com.lenin.smart_city.models.locations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="addresses")
@Table(name="addresses")
public class Address {

	@Id
	long id;
	
	@Column(name="line_1")
	String line1;
	
	@Column(name="line_2")
	String line2;
	
	String landmark;
	
	@Column(name="city_id")
	long cityId;
	
	double latitude;
	
	double longitude;
	
	
}
