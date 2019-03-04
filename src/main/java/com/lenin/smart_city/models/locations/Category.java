package com.lenin.smart_city.models.locations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="categories")
@Table(name="categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	
	public String name;

}
