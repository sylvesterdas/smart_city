package com.lenin.smart_city.models.auth;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  Long id;

    public  String email;

    public  String password;

    @ManyToOne
    public  Role role;
}