package com.lenin.smart_city.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lenin.smart_city.models.Comment;
import com.lenin.smart_city.models.ReportedPlace;
import com.lenin.smart_city.models.auth.User;
import com.lenin.smart_city.models.locations.Place;
import com.lenin.smart_city.repositories.CommentRepository;
import com.lenin.smart_city.repositories.PlaceRepository;
import com.lenin.smart_city.repositories.ReportsRepository;
import com.lenin.smart_city.repositories.UserRepository;

@RestController
public class InteractionsController {
	
	@Autowired
	PlaceRepository placeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	ReportsRepository reportsRepository;
	    
    @PostMapping("like")
    public ResponseEntity<?> like(@RequestParam(name="postId", required=true) String postId, Principal principal) {
    	if (principal == null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login to like this place");
    	}
    	
    	User user = userRepository.getOneByUsername(principal.getName());
    	
    	if (user == null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login to like this place");
    	}
    	
    	Optional<Place> placeOptions = placeRepository.findById(Long.parseLong(postId));
    	
    	if (placeOptions.isPresent()) {
    		
			Place place = placeOptions.get();
			if (place.likes.contains(user)) {
				place.likes.remove(user);
			} else {
				place.likes.add(user);
			}
    		
			placeRepository.saveAndFlush(place);
			
			return ResponseEntity.ok("Success");
		} else {
			return ResponseEntity.badRequest().body("Place not found");
		}
    }
    
    @PostMapping("comment")
    public ResponseEntity<?> comment(@RequestParam(name="postId", required=true) String postId, @RequestParam(name="message", required=true) String message, Principal principal) {
    	if (principal == null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login to like this place");
    	}
    	
    	User user = userRepository.getOneByUsername(principal.getName());
    	
    	if (user == null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login to like this place");
    	}
    	
    	Optional<Place> placeOptions = placeRepository.findById(Long.parseLong(postId));
    	
    	if (placeOptions.isPresent()) {
    		
    		Place place = placeOptions.get();
    		
    		Comment comment = new Comment();
			comment.message = message;
			comment.place = place;
			comment.user = user;
			
			commentRepository.saveAndFlush(comment);
			
			place.comments.add(comment);
    		
			placeRepository.saveAndFlush(place);
			
			return ResponseEntity.ok("Success");
		} else {
			return ResponseEntity.badRequest().body("Place not found");
		}
    }
    
    @PostMapping("report")
    public ResponseEntity<?> report(@RequestParam(name="postId", required=true) String postId, @RequestParam(name="message", required=true) String message, Principal principal) {
    	if (principal == null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login to like this place");
    	}
    	
    	User user = userRepository.getOneByUsername(principal.getName());
    	
    	if (user == null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login to like this place");
    	}
    	
    	Optional<Place> placeOptions = placeRepository.findById(Long.parseLong(postId));
    	
    	if (placeOptions.isPresent()) {
    		
    		Place place = placeOptions.get();
    		
    		ReportedPlace report = new ReportedPlace();
    		report.message = message;
    		report.place = place;
    		report.user = user;
    		
    		reportsRepository.saveAndFlush(report);
    		
    		place.reports.add(report);
    		
			placeRepository.saveAndFlush(place);
			
			return ResponseEntity.ok("Success");
		} else {
			return ResponseEntity.badRequest().body("Place not found");
		}
    }

}
