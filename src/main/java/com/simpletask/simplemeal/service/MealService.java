package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.dto.AllMealsDTO;
import com.simpletask.simplemeal.dto.ExtraDTO;
import com.simpletask.simplemeal.dto.FitMealDTO;
import com.simpletask.simplemeal.dto.RegularMealDTO;
import com.simpletask.simplemeal.enums.MealSize;
import com.simpletask.simplemeal.exception.NotFoundException;
import com.simpletask.simplemeal.model.Extra;
import com.simpletask.simplemeal.model.FitMeal;
import com.simpletask.simplemeal.model.Meal;
import com.simpletask.simplemeal.model.RegularMeal;
import com.simpletask.simplemeal.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService implements IMealService {

    @Autowired
    private MealRepository mealRepository;

    public double calculatePriceByMeal(Integer id, MealSize mealSize) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Meal not found"));

        return meal instanceof RegularMeal ?
                calculatePriceForRegularMeal((RegularMeal) meal, mealSize) :
                (meal instanceof FitMeal ?
                        ((FitMeal) meal).getPrice() :
                        (meal instanceof Extra ?
                                ((Extra) meal).getPrice() :
                                    throwNotFoundException(id)));
    }

    private double calculatePriceForRegularMeal(RegularMeal rm, MealSize mealSize) {
        return mealSize == MealSize.LARGE ? rm.getLargePrice() : rm.getSmallPrice();
    }

    private double throwNotFoundException(Integer id) {
        throw new NotFoundException("Unknown subclass type for Meal with ID: " + id);
    }

    @Override
    public AllMealsDTO getAllMeals() {
        List<Meal> meals = mealRepository.findAll();

        List<RegularMealDTO> regularMeals = meals.stream()
                .filter(meal -> meal instanceof RegularMeal)
                .map(meal -> new RegularMealDTO((RegularMeal) meal))
                .collect(Collectors.toList());

        List<FitMealDTO> fitMeals = meals.stream()
                .filter(meal -> meal instanceof FitMeal)
                .map(meal -> new FitMealDTO((FitMeal) meal))
                .collect(Collectors.toList());

        List<ExtraDTO> extras = meals.stream()
                .filter(meal -> meal instanceof Extra)
                .map(meal -> new ExtraDTO((Extra) meal))
                .collect(Collectors.toList());

        AllMealsDTO allMeals = new AllMealsDTO((ArrayList<RegularMealDTO>) regularMeals, (ArrayList<FitMealDTO>) fitMeals, (ArrayList<ExtraDTO>) extras);

        if (regularMeals.size() + fitMeals.size() + extras.size() != meals.size()) { // protiv uroka
            throw new NotFoundException("Meal not found");
        }

        return allMeals;
    }
}
