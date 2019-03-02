package com.lenin.smart_city.models.locations;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="states")
@Table(name="states")
public class State {

	@Id
	public long id;
	
	
	public String name;
	
	@ManyToOne
	public Country country;
	
}
