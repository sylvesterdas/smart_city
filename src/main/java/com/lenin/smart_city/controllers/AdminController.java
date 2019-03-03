package com.lenin.smart_city.controllers;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lenin.smart_city.repositories.RoleRepository;
import com.lenin.smart_city.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/admin")
public class AdminController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private boolean checkAdmin(String email) {
        int role = roleRepository.checkAdmin(email);
        return role == 1;
    }

    @GetMapping(path="")
    public ModelAndView adminDashboard(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
            ModelAndView m = new ModelAndView("admin/dashboard");
            m.addObject("users", userRepository.findAll());
            return m;
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
            return null;
        }
    }

    @GetMapping(path="users")
    public ModelAndView adminUsers(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
            ModelAndView m = new ModelAndView("admin/users");
            m.addObject("users", userRepository.findAll());
            return m;
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
            return null;
        }
    }

    @GetMapping(path="roles")
    public ModelAndView adminRoles(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
            ModelAndView m = new ModelAndView("admin/roles");
            m.addObject("users", roleRepository.findAll());
            return m;
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
            return null;
        }
    }

    @GetMapping(path="countries")
    public ModelAndView adminCountries(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
            ModelAndView m = new ModelAndView("admin/countries");
            m.addObject("name", principalName);
            return m;
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
            return null;
        }
    }

    @GetMapping(path="states")
    public ModelAndView adminStates(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
            ModelAndView m = new ModelAndView("admin/states");
            m.addObject("name", principalName);
            return m;
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
            return null;
        }
    }

    @GetMapping(path="cities")
    public ModelAndView adminCities(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
            ModelAndView m = new ModelAndView("admin/cities");
            m.addObject("name", principalName);
            return m;
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
            return null;
        }
    }
    

}