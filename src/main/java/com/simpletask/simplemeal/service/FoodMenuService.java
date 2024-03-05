package com.simpletask.simplemeal.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpletask.simplemeal.model.FoodMenu;
import com.simpletask.simplemeal.repository.FoodMenuRepository;

@Service
public class FoodMenuService {
	
	@Autowired
	FoodMenuRepository menuRepo;

	public List<FoodMenu> getMealsForCurrentWeek() {
		List<FoodMenu> allMeals = menuRepo.findAll();
		Collections.shuffle(allMeals);
		List<FoodMenu> randomMeals = allMeals.subList(0, 3);
		return randomMeals;
	}

}
