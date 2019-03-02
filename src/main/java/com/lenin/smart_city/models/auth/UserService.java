package com.lenin.smart_city.models.auth;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
