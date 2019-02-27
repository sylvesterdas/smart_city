package com.lenin.smart_city.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="cities")
@Entity(name="cities")
public class City {

	@Id
	long id;
	
	String name;
	
	@Column(name="state_id")
	long stateId;
	
}