package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.dto.*;
import com.simpletask.simplemeal.enums.ExtraType;
import com.simpletask.simplemeal.enums.MealSize;
import com.simpletask.simplemeal.exception.NotFoundException;
import com.simpletask.simplemeal.model.Extra;
import com.simpletask.simplemeal.model.FitMeal;
import com.simpletask.simplemeal.model.Meal;
import com.simpletask.simplemeal.model.RegularMeal;
import com.simpletask.simplemeal.repository.ExtraRepository;
import com.simpletask.simplemeal.repository.FitMealRepository;
import com.simpletask.simplemeal.repository.MealRepository;
import com.simpletask.simplemeal.repository.RegularMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService implements IMealService {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private RegularMealRepository regularMealRepository;

    @Autowired
    private FitMealRepository fitMealRepository;

    @Autowired
    private ExtraRepository extraRepository;

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

    @Override
    public RegularMealDTO addRegularMeal(CreateRegularMealDTO dto) {
        RegularMeal regularMeal = convertRegularMealDTOtoModel(dto);
        regularMeal = regularMealRepository.saveAndFlush(regularMeal);
        return new RegularMealDTO(regularMeal);
    }

    private RegularMeal convertRegularMealDTOtoModel(CreateRegularMealDTO dto) {
        return new RegularMeal(dto.getName(), dto.getDescription(), dto.getLargePrice(), dto.getSmallPrice());
    }

    @Override
    public FitMealDTO addFitMeal(CreateFitMealDTO dto) {
        FitMeal fitMeal = convertFitMealDTOtoModel(dto);
        fitMeal = fitMealRepository.saveAndFlush(fitMeal);
        return new FitMealDTO(fitMeal);
    }

    private FitMeal convertFitMealDTOtoModel(CreateFitMealDTO dto) {
        return new FitMeal(dto.getName(), dto.getDescription(), dto.getPrice(), dto.isShouldOrderEarly());
    }

    @Override
    public ExtraDTO addExtraMeal(CreateExtraDTO dto) {
        Extra extra = convertExtraMealDTOtoModel(dto);
        extra = extraRepository.saveAndFlush(extra);
        return new ExtraDTO(extra);
    }

    private Extra convertExtraMealDTOtoModel(CreateExtraDTO dto) {
        return new Extra(dto.getName(), dto.getDescription(), ExtraType.valueOf(dto.getExtraType()), dto.getPrice());
    }


}
