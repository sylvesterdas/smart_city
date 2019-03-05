package com.lenin.smart_city.controllers;

import com.lenin.smart_city.models.auth.Role;
import com.lenin.smart_city.models.auth.User;
import com.lenin.smart_city.models.locations.Country;
import com.lenin.smart_city.models.locations.Category;
import com.lenin.smart_city.models.locations.Place;
import com.lenin.smart_city.repositories.CategoriesRepository;
import com.lenin.smart_city.repositories.PlaceRepository;
import com.lenin.smart_city.repositories.RoleRepository;
import com.lenin.smart_city.repositories.UserRepository;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * AdminProcessingController
 */
@Controller
@RequestMapping(path="/admin")
public class AdminProcessingController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PlaceRepository placeRepository;
    
    @Autowired
    private CategoriesRepository categoriesRepository;
    
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    private boolean checkAdmin(String email) {
        int role = roleRepository.checkAdmin(email);
        return role == 1;
    }

    @GetMapping(path="categories")
    public ModelAndView adminCatgeories(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
            ModelAndView m = new ModelAndView("admin/categories");
            m.addObject("categories", categoriesRepository.findAll());
            return m;
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
            return null;
        }
    }
    
    @PostMapping(path="categories")
    public ModelAndView newCatgeories(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
        	Category categories = new Category();
        	categories.name = request.getParameter("category").isEmpty() ? null : request.getParameter("category");
            try {
            	if (categoriesRepository.checkExistance(categories.name) > 0)
                    throw new DataIntegrityViolationException("Category already exist.");
                categoriesRepository.saveAndFlush(categories);
            } catch (Exception e) {
                ModelAndView m = new ModelAndView("admin/categories");
                m.addObject("errors", new String[]{"Something went wrong."});
                e.printStackTrace();
                m.addObject("categories", categoriesRepository.findAll());
                return m;
            }
            redirectStrategy.sendRedirect(request, response, "/admin/categories");
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
        }
        return null;
    }
    
    @GetMapping(path="places")
    public ModelAndView adminPlaces(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
            ModelAndView m = new ModelAndView("admin/places");
            m.addObject("places", placeRepository.findAll());
            return m;
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
            return null;
        }
    }
    
    
}