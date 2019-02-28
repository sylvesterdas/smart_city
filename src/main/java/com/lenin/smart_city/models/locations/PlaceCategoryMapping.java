package com.lenin.smart_city.models.locations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="place_category_mappings")
@Table(name="place_category_mappings")
public class PlaceCategoryMapping {

	@Id
	long id;
	
	@Column(name="place_id")
	long placeId;
	
	@Column(name="category_id")
	long categoryId;
	
}
