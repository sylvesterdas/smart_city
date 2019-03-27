package com.lenin.smart_city.models.auth;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @ManyToOne
    public  Role role;
}