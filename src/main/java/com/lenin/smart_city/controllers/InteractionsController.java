package com.lenin.smart_city.controllers;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InteractionsController {
	    
    @PostMapping("like")
    public ResponseEntity<?> like(@RequestParam(name="postId", required=true) String postId, Principal principal) {
    	if (principal == null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login to like this place");
    	}
    	
    	
    	String message = "{\"status\":true, \"message\":\""+principal.getName()+"\"}";
    	return ResponseEntity.ok(message);
    }

}
