package com.simpletask.simplemeal.controllers;

import com.simpletask.simplemeal.dto.*;
import com.simpletask.simplemeal.service.IMealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/meal")
@Validated
public class MealController {

    @Autowired
    private IMealService mealService;

    @GetMapping
    public ResponseEntity<AllMealsDTO> getAllMeals() throws Exception {
        return new ResponseEntity<AllMealsDTO>(mealService.getAllMeals(), HttpStatus.OK);
    }

    @PostMapping("/regular")
    public ResponseEntity<RegularMealDTO> addRegularMeal(@Valid @RequestBody CreateRegularMealDTO dto, BindingResult bindingResult) {
        return new ResponseEntity<RegularMealDTO>(mealService.addRegularMeal(dto), HttpStatus.CREATED);
    }

    @PostMapping("/fit")
    public ResponseEntity<FitMealDTO> addFitMeal(@Valid @RequestBody CreateFitMealDTO dto, BindingResult bindingResult) {
        return new ResponseEntity<FitMealDTO>(mealService.addFitMeal(dto), HttpStatus.CREATED);
    }

    @PostMapping("/extra")
    public ResponseEntity<ExtraDTO> addFitMeal(@Valid @RequestBody CreateExtraDTO dto, BindingResult bindingResult) {
        return new ResponseEntity<ExtraDTO>(mealService.addExtraMeal(dto), HttpStatus.CREATED);
    }
}
