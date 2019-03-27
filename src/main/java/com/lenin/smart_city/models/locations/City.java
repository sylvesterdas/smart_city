package com.lenin.smart_city.models.locations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name="cities", uniqueConstraints=@UniqueConstraint(columnNames={"name"}))
@Entity(name="cities")
public class City {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	public long id;
	
	public String name;
	
	@ManyToOne
	public State state;
	
}