package com.lenin.smart_city.models.auth;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name = "roles", uniqueConstraints=@UniqueConstraint(columnNames={"name"}))
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long id;

    @NotBlank
    public String name;

    @OneToMany(mappedBy = "role")
    public Set<User> users;

    
}