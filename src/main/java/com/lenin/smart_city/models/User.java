package com.lenin.smart_city.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name="users")
public class User {
	
	@Id
	int id;
	
	@Email
	String email;
	
	String password;
	
	@Column(name="role_id")
	int roleId;

}
