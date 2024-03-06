package com.simpletask.simplemeal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpletask.simplemeal.dto.ListOfMealsDTO;
import com.simpletask.simplemeal.mapper.FoodMenuMapper;
import com.simpletask.simplemeal.model.FoodMenu;
import com.simpletask.simplemeal.service.FoodMenuService;

@RestController
@RequestMapping("/api/meals")
public class FoodMenuController {
	
	@Autowired
	FoodMenuService menuService;
	
	@GetMapping("/this-week")
	public List<ListOfMealsDTO> getMealsForThisWeek() {
		List<FoodMenu> meals = menuService.getMealsForCurrentWeek();
        List<ListOfMealsDTO> mealDTOs = FoodMenuMapper.toDTOList(meals);
        return mealDTOs;
	}

}
