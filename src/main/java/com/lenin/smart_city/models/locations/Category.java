package com.lenin.smart_city.models.locations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity(name="categories")
@Table(name="categories", uniqueConstraints=@UniqueConstraint(columnNames={"name"}))
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	public long id;
	
	@NotBlank
	public String name;

}
