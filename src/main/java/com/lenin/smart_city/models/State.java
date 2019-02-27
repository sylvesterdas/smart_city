package com.lenin.smart_city.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="states")
@Table(name="states")
public class State {

	@Id
	long id;
	
	
	String name;
	
	@Column(name="country_id")
	long countryId;
	
}
