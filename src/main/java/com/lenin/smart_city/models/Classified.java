package com.lenin.smart_city.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="classifieds")
@Entity(name="classifieds")
public class Classified {
	
	@Id
	long id;
	
	String title;
	
	String details;
	
	double latitude;
	
	double longitude;
	
	@Column(name="city_id")
	long cityId;
	
	@Column(name="posted_at")
	Timestamp postedAt;
	
	@Column(name="user_id")
	long userId;

}
