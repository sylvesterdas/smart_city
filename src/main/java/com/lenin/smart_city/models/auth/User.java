package com.lenin.smart_city.models.auth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
	@Email
	public String email;
	
	public String password;
	
	@ManyToOne
	public Role role;
}
