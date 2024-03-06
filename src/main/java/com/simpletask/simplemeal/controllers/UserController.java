package com.simpletask.simplemeal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simpletask.simplemeal.model.User;
import com.simpletask.simplemeal.services.UserService;
import com.simpletask.simplemeal.services.UserServiceI;

import dto.UserDTO;
import exceptions.InvalidRegisterException;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	private  UserServiceI userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	  public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) throws Exception {
      		System.out.print(userDTO);
            User savedUser =userService.registerUser(userDTO);
            if (savedUser!=null)
            	return  new ResponseEntity<>("Registration successful", HttpStatus.OK);
            else 
            	throw new InvalidRegisterException("Registration failed");
            
    }
	
	 
}
