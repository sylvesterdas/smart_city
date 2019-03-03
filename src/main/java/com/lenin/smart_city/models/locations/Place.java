package com.lenin.smart_city.models.locations;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="places")
@Entity(name="places")
public class Place {
	
	@Id
	public long id;
	
	public String title;
	
	public String details;
	
	public String picture;
	
	@OneToOne
	public Address address;

	@ManyToMany
	public Collection<Category> categories;
}
