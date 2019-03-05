package com.lenin.smart_city.controllers;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lenin.smart_city.models.locations.Address;
import com.lenin.smart_city.models.locations.Category;
import com.lenin.smart_city.models.locations.City;
import com.lenin.smart_city.models.locations.Place;
import com.lenin.smart_city.models.trips.Itenary;
import com.lenin.smart_city.models.trips.Trip;
import com.lenin.smart_city.repositories.AddressRepository;
import com.lenin.smart_city.repositories.CategoriesRepository;
import com.lenin.smart_city.repositories.CitiesRepository;
import com.lenin.smart_city.repositories.PlacesRepository;
import com.lenin.smart_city.repositories.RoleRepository;
import com.lenin.smart_city.repositories.TripsRepository;

@Controller
@RequestMapping(path="/admin")
public class TripController {
	
	@Autowired
	private TripsRepository tripsRepository;
	@Autowired
	private CategoriesRepository categoriesRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PlacesRepository placesRepository;
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CitiesRepository cityRepository;
	
	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
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
	
	@GetMapping(path="trips")
	public ModelAndView adminTrips(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
		String name = principal.getName();
		
		if (checkAdmin(name)) {
			
			ModelAndView m = new ModelAndView("admin/trips");
			m.addObject("trips", tripsRepository.findAll());
			m.addObject("places", placesRepository.findAll());
			return m;
		} else {
			redirectStrategy.sendRedirect(request, response, "/");
		}
		
		return null;
	}
	
	@GetMapping(path="places")
	public ModelAndView adminPlaces(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
		String name = principal.getName();
		
		if (checkAdmin(name)) {
			
			ModelAndView m = new ModelAndView("admin/places");
			m.addObject("categories", categoriesRepository.findAll());
			m.addObject("places", placesRepository.findAll());
			m.addObject("cities", cityRepository.findAll());
			return m;
		} else {
			redirectStrategy.sendRedirect(request, response, "/");
		}
		
		return null;
	}

	@PostMapping(path="trips")
	public ModelAndView newTrip(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException, ParseException {
		String name = principal.getName();
		
		if (checkAdmin(name)) {
			String title = request.getParameter("title");
			String startDate = request.getParameter("start_date");
			String endDate = request.getParameter("end_date");
			
			Place[] place = new Place[4];
			String[] startTime = new String[4];
			String[] endTime = new String[4];
			
			place[0] = placesRepository.findById(Long.parseLong(request.getParameter("place-1"))).get();
			place[1] = placesRepository.findById(Long.parseLong(request.getParameter("place-2"))).get();
			place[2] = placesRepository.findById(Long.parseLong(request.getParameter("place-3"))).get();
			place[3] = placesRepository.findById(Long.parseLong(request.getParameter("place-4"))).get();
			
			startTime[0] = request.getParameter("start-date-1") + " " + request.getParameter("start-time-1");
			startTime[1] = request.getParameter("start-date-2") + " " + request.getParameter("start-time-2");
			startTime[2] = request.getParameter("start-date-3") + " " + request.getParameter("start-time-3");
			startTime[3] = request.getParameter("start-date-4") + " " + request.getParameter("start-time-4");
			
			endTime[0] = request.getParameter("end-date-1") + " " + request.getParameter("end-time-1");
			endTime[1] = request.getParameter("end-date-2") + " " + request.getParameter("end-time-2");
			endTime[2] = request.getParameter("end-date-3") + " " + request.getParameter("end-time-3");
			endTime[3] = request.getParameter("end-date-4") + " " + request.getParameter("end-time-4");
			
			Trip trip = new Trip();
			trip.startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			trip.endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			trip.bookings = new HashSet<>();
			
			
			try {
				Set<Itenary> itenaries = new HashSet<>();
				for (int i =0; i<4; i++) {
					Itenary itenary = new Itenary();
					
					itenary.startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startTime[i]);
					itenary.endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endTime[i]);
					itenary.place = place[i];
					
					itenaries.add(itenary);
				}
				trip.name = title;
				trip.itenaries = itenaries;
				trip = tripsRepository.saveAndFlush(trip);
			} catch (DataIntegrityViolationException e) {
				ModelAndView m = new ModelAndView("admin/trips");
				m.addObject("errors", new String[] {"Something went wrong"});
				m.addObject("trips", tripsRepository.findAll());
				m.addObject("places", placesRepository.findAll());
				return m;
			}
		} else {
			redirectStrategy.sendRedirect(request, response, "/");
		}
		
		return null;
	}
	
	@PostMapping(path="places")
	public ModelAndView newPlace(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException, ParseException {
		String name = principal.getName();
		
		if (checkAdmin(name)) {
			String title = request.getParameter("name");
			String detail = request.getParameter("details");
			String[] categories = request.getParameterValues("categories");
			
			String addressLine1 = request.getParameter("line1");
			String addressLine2 = request.getParameter("line2");
			String landmark = request.getParameter("landmark");
			String latitude = request.getParameter("latitude");
			String longitude = request.getParameter("longitude");
			City city = cityRepository.findById(Long.parseLong(request.getParameter("cities"))).get();
			
			try {
				Place place = new Place();
				place.title = title;
				place.details = detail;
				place.address = new Address();
				place.address.line1 = addressLine1;
				place.address.line2 = addressLine2;
				place.address.landmark = landmark;
				place.address.city = city;
				place.address.latitude = Double.parseDouble(latitude);
				place.address.longitude = Double.parseDouble(longitude);
				place.address = addressRepository.saveAndFlush(place.address);
				place.categories = new HashSet<>();
				for (String categoryId : categories) {
					Long catId = Long.parseLong(categoryId);
					Category category = categoriesRepository.getOne(catId);
					place.categories.add(category);
				}
				placesRepository.saveAndFlush(place);
			} catch (DataIntegrityViolationException e) {
				ModelAndView m = new ModelAndView("admin/trips");
				m.addObject("errors", new String[] {"Something went wrong"});
				m.addObject("trips", tripsRepository.findAll());
				m.addObject("places", placesRepository.findAll());
				return m;
			}
			redirectStrategy.sendRedirect(request, response, "/admin/places");
		} else {
			redirectStrategy.sendRedirect(request, response, "/");
		}
		
		return null;
	}
	

}
