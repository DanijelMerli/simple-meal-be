package com.simpletask.simplemeal.controllers;

import com.simpletask.simplemeal.dto.AllMealsDTO;
import com.simpletask.simplemeal.service.IMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/meal")
public class MealController {

    @Autowired
    private IMealService mealService;

    @GetMapping
    public ResponseEntity<AllMealsDTO> getAllMeals() throws Exception {
        return new ResponseEntity<AllMealsDTO>(mealService.getAllMeals(), HttpStatus.OK);
    }
}
