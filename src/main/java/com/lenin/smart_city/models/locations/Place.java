package com.lenin.smart_city.models.locations;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lenin.smart_city.models.Comment;
import com.lenin.smart_city.models.ReportedPlace;
import com.lenin.smart_city.models.auth.User;

@Table(name="places", uniqueConstraints=@UniqueConstraint(columnNames={"title"}))
@Entity(name="places")
public class Place {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	public long id;
	
	public String title;
	
	@Column(columnDefinition = "LONGTEXT")
	public String details;
	
	public String picture;
	
	@OneToOne
	public Address address;
	
	@OneToOne
	public User author;
	
	@ManyToMany(cascade=CascadeType.ALL)
	public Set<User> likes;
	
	@OneToMany
	public Set<Comment> comments;
	
	@OneToMany
	public Set<ReportedPlace> reports;

	@ManyToMany
	public Collection<Category> categories;
}
