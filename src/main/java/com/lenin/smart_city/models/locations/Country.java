package com.lenin.smart_city.models.locations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name="countries")
@Table(name="countries", uniqueConstraints=@UniqueConstraint(columnNames={"name"}))
public class Country {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	
	public String name;

}
