package com.lenin.smart_city.models.trips;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="posts")
@Table(name="posts")
public class Post {
	
	@Id
	long id;
	
	String title;
	
	@Column(name="place_id")
	long placeId;
	
	@Column(name="posted_at")
	Timestamp postedAt;
	
	@Column(name="posted_by")
	long postedBy;
	
	@Column(name="view_count")
	int viewCount;

}
