package com.simpletask.simplemeal.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpletask.simplemeal.dto.WeeklyMenuDTO;
import com.simpletask.simplemeal.service.WeeklyMenuService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/meals")
public class WeeklyMenuController {
	
	@Autowired
	WeeklyMenuService weekService;
	
	@GetMapping("/this-week")
    public ResponseEntity<WeeklyMenuDTO> getWeeklyMenu() {
        WeeklyMenuDTO weeklyMenuDTO = weekService.getWeeklyMenuByStartDate();
        if (weeklyMenuDTO != null) {
            return new ResponseEntity<>(weeklyMenuDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}