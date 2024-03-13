package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.enums.MealSize;
import com.simpletask.simplemeal.model.Meal;

public interface IMealService {
    public double calculatePriceByMeal(Integer id, MealSize mealSize);
}
