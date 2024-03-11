package com.simpletask.simplemeal.controllers;

import com.simpletask.simplemeal.dto.LoginRequestDTO;
import com.simpletask.simplemeal.dto.LoginResponseDTO;
import com.simpletask.simplemeal.dto.UserDTO;
import com.simpletask.simplemeal.exception.InvalidRegisterException;
import com.simpletask.simplemeal.model.User;
import com.simpletask.simplemeal.service.IUserService;
import com.simpletask.simplemeal.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/auth/")
public class AuthenticationController {

    @Autowired
    private IUserService userService;


    @PostMapping("login")
    public ResponseEntity<?> postLogin(@RequestBody LoginRequestDTO loginRequestDTO) {
        return new ResponseEntity<LoginResponseDTO>(userService.login(loginRequestDTO), HttpStatus.OK);
    }

    @PostMapping("dummyRegistration")
    public ResponseEntity<?> postReg() {
        //userService.addDummy();
        return new ResponseEntity<>(HttpStatus.OK);
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
