package com.lenin.smart_city.models.auth;

import javax.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints=@UniqueConstraint(columnNames={"email"}))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  Long id;

    public  String email;

    public  String fullname;
    
    public  Long age;
    
    public String dob;
    
    public String image;
    
    public  String password;

    @ManyToOne
    public  Role role;
}