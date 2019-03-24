package com.lenin.smart_city.controllers;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.HashSet;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lenin.smart_city.models.locations.Address;
import com.lenin.smart_city.models.locations.Category;
import com.lenin.smart_city.models.locations.City;
import com.lenin.smart_city.models.locations.Place;
import com.lenin.smart_city.repositories.AddressRepository;
import com.lenin.smart_city.repositories.CategoriesRepository;
import com.lenin.smart_city.repositories.CitiesRepository;
import com.lenin.smart_city.repositories.PlaceRepository;
import com.lenin.smart_city.repositories.RoleRepository;
import com.lenin.smart_city.repositories.UserRepository;
import com.lenin.smart_city.storage.StorageService;

@Controller
@RequestMapping(path="/admin")
public class TripController {
	
	@Autowired
	private CategoriesRepository categoriesRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlaceRepository placesRepository;
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CitiesRepository cityRepository;
	
	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	private final StorageService storageService;

    @Autowired
    public TripController(StorageService storageService) {
        this.storageService = storageService;
    }
    
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
	
	@PostMapping(path="places")
	public ModelAndView newPlace(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException, ParseException {
		String name = principal.getName();
		
		if (checkAdmin(name)) {
			String title = request.getParameter("name");
			String detail = request.getParameter("details");
			String[] categories = request.getParameterValues("categories");
			
			String addressLine1 = request.getParameter("line1");
			String addressLine2 = request.getParameter("line2");
			String landmark = request.getParameter("landmark");
			City city = cityRepository.findById(Long.parseLong(request.getParameter("cities"))).get();
			
			try {
				Place place = new Place();
				place.title = title;
				place.details = detail;
				if (file != null && !file.isEmpty()) {
					storageService.store(file, StorageService.TYPE.PLACES);
					String image = file.getOriginalFilename();
					place.picture = image;
				}
				place.author = userRepository.getOneByUsername(name);
				place.address = new Address();
				place.address.line1 = addressLine1;
				place.address.line2 = addressLine2;
				place.address.landmark = landmark;
				place.address.city = city;
				/*
				 * place.address.latitude = Double.parseDouble(latitude);
				 * place.address.longitude = Double.parseDouble(longitude);
				 */
				place.address = addressRepository.saveAndFlush(place.address);
				place.categories = new HashSet<>();
				for (String categoryId : categories) {
					Long catId = Long.parseLong(categoryId);
					Category category = categoriesRepository.getOne(catId);
					place.categories.add(category);
				}
				placesRepository.saveAndFlush(place);
			} catch (DataIntegrityViolationException e) {
				e.printStackTrace();
				ModelAndView m = new ModelAndView("admin/places");
				m.addObject("errors", new String[] {"Something went wrong"});
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
