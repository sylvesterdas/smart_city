package com.lenin.smart_city.models.auth;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.lenin.smart_city.models.locations.Place;

@Entity
@Table(name = "users", uniqueConstraints=@UniqueConstraint(columnNames={"email"}))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public  Long id;

    @NotBlank
    public  String email;

    @NotBlank
    public  String fullname;
    
    public  Long age;
    
    public String dob;
    
    public String image;
    
    public  String password;
    
    @ManyToMany(cascade=CascadeType.ALL, mappedBy="likes")
    public Set<Place> likedPlaces;

    @ManyToOne
    public  Role role;
}