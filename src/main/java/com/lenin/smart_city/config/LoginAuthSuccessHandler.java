package com.lenin.smart_city.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lenin.smart_city.models.auth.Role;
import com.lenin.smart_city.repositories.RoleRepository;

import java.io.IOException;
import java.util.logging.Logger;

public class LoginAuthSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger log = Logger.getLogger(LoginAuthSuccessHandler.class.getName());

    @Autowired
    private RoleRepository roleRepository;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        
        User user = (User) authentication.getPrincipal();
        Role role = roleRepository.findByUserEmail(user.getUsername());

        switch (role.getId().intValue()) {
            case 1: // Super Admin
                redirectStrategy.sendRedirect(request, response, "/admin");
                break;
            case 2: // Admin
                redirectStrategy.sendRedirect(request, response, "/admin");
                break;
            case 3: // User
                redirectStrategy.sendRedirect(request, response, "/");
                break;
        
            default:
                redirectStrategy.sendRedirect(request, response, "/");
                break;
        }

    }

    
}