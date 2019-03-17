package com.lenin.smart_city.controllers;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lenin.smart_city.models.auth.Role;
import com.lenin.smart_city.models.auth.User;
import com.lenin.smart_city.repositories.PlaceRepository;
import com.lenin.smart_city.repositories.RoleRepository;
import com.lenin.smart_city.repositories.UserRepository;
import com.lenin.smart_city.storage.StorageService;
import com.lenin.smart_city.storage.StorageService.TYPE;

/**
 * MainController
 */
@Controller
@RequestMapping(path="/")
public class MainController {

	private final StorageService storageService;

    @Autowired
    public MainController(StorageService storageService) {
        this.storageService = storageService;
    }
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private PlaceRepository placeRepository;
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
    @GetMapping(path="")
    public String index(Model model, @RequestParam(name="place", required=false) String place) {
    	if (place == null) {
    		
    		model.addAttribute("places", placeRepository.findAll());
    	} else {
    		model.addAttribute("places", placeRepository.getByName(place));
    	}
        return "welcome";
    }

    @GetMapping(path="registration")
    public String registration(Principal principal) {
        return "registration";
    }
    
    @GetMapping(path="profile")
    public ModelAndView profile(HttpServletRequest request, HttpServletResponse response, Principal principal) {
    	String principalName = principal.getName();
    	List<User> ud = userRepository.findAll();
    	User theThingUWant = new User();
    	for (User user : ud) {
			if  (user.email.equalsIgnoreCase(principalName)) {
				 theThingUWant = user;
				break;
			}
		}
    	
    	
    	ModelAndView m = new ModelAndView("profile");
		m.addObject("theThingUWant", theThingUWant);
		m.addObject("image", "files/" + theThingUWant.image);
		return m;
    }
    
    @GetMapping("/profile/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename, TYPE.PROFILE);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    
    @PostMapping(path="profile")
    public ModelAndView updateprofile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException, ParseException  {
    		
    	String fullname = request.getParameter("name");
		String age = request.getParameter("age");
		String dob = request.getParameter("dob");
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		User thingUWant = userRepository.findById(Long.parseLong(id)).get();
		
		try {
			thingUWant.fullname = fullname;
			thingUWant.age = Long.parseLong(age);
			thingUWant.dob = dob;
			thingUWant.email = email;
			if (file != null && !file.isEmpty()) {
				storageService.store(file, StorageService.TYPE.PROFILE);
				String image = file.getOriginalFilename();
				thingUWant.image = image;
			}
			userRepository.saveAndFlush(thingUWant);
			redirectStrategy.sendRedirect(request, response, "/profile");
			
		}catch (DataIntegrityViolationException e) { 
			ModelAndView m = new ModelAndView("profile");
			m.addObject("theThingUWant", thingUWant);
			return m;
		}
		
		return null;
		
    }
    
    @PostMapping(path="registration")
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException {
    	
    	User user = new User();
        Role role = roleRepository.getRoleById(3);
        user.role = role;
        user.email = request.getParameter("email");
        user.fullname = request.getParameter("firstname")+' '+request.getParameter("lastname");
        user.password = bCryptPasswordEncoder.encode(request.getParameter("password"));
        
        try {
            userRepository.saveAndFlush(user);
        } catch (DataIntegrityViolationException e) {
            ModelAndView m = new ModelAndView("/registration");
            m.addObject("errors", new String[]{"Duplicate email"});
            m.addObject("roles", roleRepository.findAll());
            m.addObject("users", userRepository.findAll());
            return m;
        }
    	
    	redirectStrategy.sendRedirect(request, response, "/login");
        return null;
    }
    
    

    @GetMapping(path="error")
    public String error(Principal principal) {
        return "error/404";
    }
    
}