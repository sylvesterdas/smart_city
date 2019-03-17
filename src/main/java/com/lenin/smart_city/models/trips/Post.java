package com.lenin.smart_city.models.trips;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.lenin.smart_city.models.auth.User;
import com.lenin.smart_city.models.interactions.Rating;
import com.lenin.smart_city.models.locations.Place;

@Entity(name="posts")
@Table(name="posts")
public class Post {
	
	@Id
	public long id;
	
	@Column(length=50)
	public String title;

	@Column(length=100)
	public String subtitle;
	
	@Column(columnDefinition="text")
	public String body;	
	
	@ManyToOne
	public Place place;
	
	@ManyToOne
	public User postedBy;
	
	@Column(name="view_count")
	public int viewCount;
	
	@OneToMany
	public Set<User> likes;
	
	@OneToMany
	public Set<User> dislikes;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	public Set<Rating> ratings;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	public Date updated;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	public Date posted;
	
}
