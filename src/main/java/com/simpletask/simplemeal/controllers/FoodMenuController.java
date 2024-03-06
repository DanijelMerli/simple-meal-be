package com.simpletask.simplemeal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpletask.simplemeal.dto.ListOfMealsDTO;
import com.simpletask.simplemeal.mapper.FoodMenuMapper;
import com.simpletask.simplemeal.model.Meal;
import com.simpletask.simplemeal.service.MealService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/meals")
public class FoodMenuController {
	
	@Autowired
	MealService menuService;
	
	@GetMapping("/this-week")
	public List<ListOfMealsDTO> getMealsForThisWeek() {
		List<Meal> meals = menuService.getMealsForCurrentWeek();
        List<ListOfMealsDTO> mealDTOs = FoodMenuMapper.toDTOList(meals);
        return mealDTOs;
	}

}
