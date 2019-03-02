package com.lenin.smart_city.models.locations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="places")
@Entity(name="places")
public class Place {
	
	@Id
	long id;
	
	String title;
	
	String details;

	@Column(name="address_id")
	long addressId;
	
	String picture;

}
