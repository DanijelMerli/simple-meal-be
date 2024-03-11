package com.simpletask.simplemeal.controllers;

import com.simpletask.simplemeal.dto.LoginRequestDTO;
import com.simpletask.simplemeal.dto.LoginResponseDTO;
import com.simpletask.simplemeal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/")
public class AuthenticationController {

    @Autowired
    private UserService userService;


    @PostMapping("login")
    public ResponseEntity<?> postLogin(@RequestBody LoginRequestDTO loginRequestDTO) {
        return new ResponseEntity<LoginResponseDTO>(userService.login(loginRequestDTO), HttpStatus.OK);
    }

    @PostMapping("dummyRegistration")
    public ResponseEntity<?> postReg() {
        userService.addDummy();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
