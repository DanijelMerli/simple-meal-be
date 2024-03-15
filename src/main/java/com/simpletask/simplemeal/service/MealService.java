package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.enums.MealSize;
import com.simpletask.simplemeal.exception.NotFoundException;
import com.simpletask.simplemeal.model.Extra;
import com.simpletask.simplemeal.model.FitMeal;
import com.simpletask.simplemeal.model.Meal;
import com.simpletask.simplemeal.model.RegularMeal;
import com.simpletask.simplemeal.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    java 8- neclean verzija ko voli
//    public double calculatePriceByMeal(Integer id, MealSize mealSize) {
//        Meal meal = mealRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Meal not found"));
//        if (meal instanceof RegularMeal) {
//            RegularMeal rm = regularMealRepository.findById(id)
//                    .orElseThrow(() -> new NotFoundException("Regular Meal not found"));
//            if (mealSize.equals(MealSize.Large)) {
//                return rm.getLargePrice();
//            }
//            return rm.getSmallPrice();
//        } else if (meal instanceof FitMeal) {
//            FitMeal fm = fitMealRepository.findById(id)
//                    .orElseThrow(() -> new NotFoundException("Fit Meal not found"));
//            return fm.getPrice();
//        } else if (meal instanceof Extra) {
//            Extra e = extraRepository.findById(id)
//                    .orElseThrow(() -> new NotFoundException("Extra Meal not found"));
//            return e.getPrice();
//        } else {
//            throw new NotFoundException("Unknown subclass type for Meal with ID: " + id);
//        }
//    }
}
