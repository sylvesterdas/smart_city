package com.lenin.smart_city.models.locations;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="countries")
@Table(name="countries")
public class Country {
	
	@Id
	public long id;
	
	public String name;

}
