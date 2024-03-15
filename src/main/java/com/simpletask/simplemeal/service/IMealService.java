package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.dto.*;
import com.simpletask.simplemeal.enums.MealSize;
import com.simpletask.simplemeal.model.Meal;

public interface IMealService {
    public double calculatePriceByMeal(Integer id, MealSize mealSize);
    public AllMealsDTO getAllMeals();
    RegularMealDTO addRegularMeal(CreateRegularMealDTO dto);
    FitMealDTO addFitMeal(CreateFitMealDTO dto);
    ExtraDTO addExtraMeal(CreateExtraDTO dto);
}
