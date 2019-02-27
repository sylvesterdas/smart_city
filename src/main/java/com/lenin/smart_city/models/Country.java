package com.lenin.smart_city.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="countries")
@Table(name="countries")
public class Country {
	
	@Id
	long id;
	
	String name;

}
