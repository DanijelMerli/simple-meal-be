package com.simpletask.simplemeal.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpletask.simplemeal.model.Meal;
import com.simpletask.simplemeal.repository.MealRepository;

@Service
public class MealService {
	
	@Autowired
	MealRepository menuRepo;

	public List<Meal> getMealsForCurrentWeek() {
		List<Meal> allMeals = menuRepo.findAll();
		Collections.shuffle(allMeals);
		List<Meal> randomMeals = allMeals.subList(0, 3);
		return randomMeals;
	}

}
