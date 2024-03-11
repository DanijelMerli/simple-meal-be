package com.simpletask.simplemeal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpletask.simplemeal.dto.UserDTO;
import com.simpletask.simplemeal.exception.InvalidRegisterException;
import com.simpletask.simplemeal.model.User;
import com.simpletask.simplemeal.service.UserService;
import com.simpletask.simplemeal.service.IUserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/user/")
public class UserController {
	
	private IUserService userService;
	
	@Autowired
	public UserController(UserService userService) {

		this.userService = userService;
	}

	@PostMapping(value="register", consumes = MediaType.APPLICATION_JSON_VALUE, 
			  produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) throws Exception {
		try {
			   User savedUser =userService.registerUser(userDTO);
            	return  new ResponseEntity<>(HttpStatus.OK);
            } catch(Exception e) {
            	
            	throw new InvalidRegisterException("Registration failed");
            }
    }
	
	 
}
