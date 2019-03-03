package com.lenin.smart_city.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lenin.smart_city.models.auth.Role;
import com.lenin.smart_city.models.auth.User;
import com.lenin.smart_city.models.locations.City;
import com.lenin.smart_city.models.locations.Country;
import com.lenin.smart_city.models.locations.State;
import com.lenin.smart_city.repositories.CitiesRepository;
import com.lenin.smart_city.repositories.CountriesRepository;
import com.lenin.smart_city.repositories.RoleRepository;
import com.lenin.smart_city.repositories.StatesRepository;
import com.lenin.smart_city.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getILoggerFactory().getLogger(AdminController.class.getSimpleName());

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountriesRepository countriesRepository;
    
    @Autowired
    private CitiesRepository citiesRepository;

    @Autowired
    private StatesRepository statesRepository;

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

    @PostMapping(path="users")
    public ModelAndView adminUsers(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
            User user = new User();
            Role role = roleRepository.getRoleById(Long.parseLong(request.getParameter("role")));
            user.role = role;
            user.email = request.getParameter("email");
            user.password = bCryptPasswordEncoder.encode(request.getParameter("password"));
            try {
                userRepository.saveAndFlush(user);
            } catch (DataIntegrityViolationException e) {
                ModelAndView m = new ModelAndView("admin/users");
                m.addObject("errors", new String[]{"Duplicate email"});
                m.addObject("roles", roleRepository.findAll());
                m.addObject("users", userRepository.findAll());
                return m;
            }
            redirectStrategy.sendRedirect(request, response, "/admin/users");
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
        }
        return null;
    }

    @GetMapping(path="users")
    public ModelAndView newUsers(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
            ModelAndView m = new ModelAndView("admin/users");
            m.addObject("roles", roleRepository.findAll());
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
            m.addObject("roles", roleRepository.findAll());
            return m;
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
            return null;
        }
    }

    @PostMapping(path="countries")
    public ModelAndView newCountry(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
            Country countries = new Country();
            countries.name = request.getParameter("country");
            try {
                countriesRepository.saveAndFlush(countries);
            } catch (DataIntegrityViolationException e) {
                ModelAndView m = new ModelAndView("admin/countries");
                m.addObject("errors", new String[]{"Duplicate country"});
                m.addObject("countries", countriesRepository.findAll());
                return m;
            }
            redirectStrategy.sendRedirect(request, response, "/admin/countries");
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
        }
        return null;
    }

    @GetMapping(path="countries")
    public ModelAndView adminCountries(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
            ModelAndView m = new ModelAndView("admin/countries");
            m.addObject("countries", countriesRepository.findAll());
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
            m.addObject("states", statesRepository.findAll());
            m.addObject("countries", countriesRepository.findAll());
            return m;
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
            return null;
        }
    }

    @PostMapping(path="states")
    public ModelAndView newState(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
            State states = new State();
            Country country = countriesRepository.findById(Long.parseLong(request.getParameter("country"))).get();
            states.name = request.getParameter("states");
            states.country = country;
            try {
                if (statesRepository.checkExistance(states.name, country.name) > 0)
                    throw new DataIntegrityViolationException("State already exists in the country");
                statesRepository.saveAndFlush(states);
            } catch (DataIntegrityViolationException e) {
                ModelAndView m = new ModelAndView("admin/states");
                m.addObject("errors", new String[]{"Duplicate state"});
                m.addObject("states", statesRepository.findAll());
                m.addObject("countries", countriesRepository.findAll());
                return m;
            }
            redirectStrategy.sendRedirect(request, response, "/admin/states");
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
        }
        return null;
    }

    @GetMapping(path="cities")
    public ModelAndView adminCities(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
            ModelAndView m = new ModelAndView("admin/cities");
            m.addObject("cities", citiesRepository.findAll());
            m.addObject("states", statesRepository.findAll());
            return m;
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
            return null;
        }
    }

    @PostMapping(path="cities")
    public ModelAndView newCity(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
        String principalName = principal.getName();

        if (checkAdmin(principalName)) {
            City cities = new City();
            State state = statesRepository.findById(Long.parseLong(request.getParameter("state"))).get();
            cities.name = request.getParameter("city");
            cities.state = state;
            try {
                if (citiesRepository.checkExistance(state.name, cities.name) > 0)
                    throw new DataIntegrityViolationException("City already exists in the state");
                citiesRepository.saveAndFlush(cities);
            } catch (DataIntegrityViolationException e) {
                ModelAndView m = new ModelAndView("/admin/cities");
                m.addObject("errors", new String[]{"Duplicate city"});
                m.addObject("cities", citiesRepository.findAll());
                m.addObject("states", statesRepository.findAll());
                return m;
            }
            redirectStrategy.sendRedirect(request, response, "/admin/cities");
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
        }
        return null;
    }
    

}