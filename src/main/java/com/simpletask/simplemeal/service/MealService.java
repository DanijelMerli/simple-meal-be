package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.dto.*;
import com.simpletask.simplemeal.enums.ExtraType;
import com.simpletask.simplemeal.enums.MealSize;
import com.simpletask.simplemeal.exception.NotFoundException;
import com.simpletask.simplemeal.model.*;
import com.simpletask.simplemeal.repository.*;
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

    @Autowired
    private DailyMenuRepository dailyMenuRepository;

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

        if (regularMeals.size() + fitMeals.size() + extras.size() != meals.size()) {
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

    @Override
    public RegularMealDTO editRegularMeal(CreateRegularMealDTO dto, int id) {
        RegularMeal regularMeal = regularMealRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Regular meal not found"));
        regularMeal.setName(dto.getName());
        regularMeal.setDescription(dto.getDescription());
        regularMeal.setLargePrice(dto.getLargePrice());
        regularMeal.setSmallPrice(dto.getSmallPrice());
        regularMeal = regularMealRepository.saveAndFlush(regularMeal);
        return new RegularMealDTO(regularMeal);
    }

    @Override
    public FitMealDTO editFitMeal(CreateFitMealDTO dto, int id) {
        FitMeal fitMeal = fitMealRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fit meal not found"));
        fitMeal.setName(dto.getName());
        fitMeal.setDescription(dto.getDescription());
        fitMeal.setPrice(dto.getPrice());
        fitMeal.setShouldOrderEarly(dto.isShouldOrderEarly());
        fitMeal = fitMealRepository.saveAndFlush(fitMeal);
        return new FitMealDTO(fitMeal);
    }

    @Override
    public ExtraDTO editExtraMeal(CreateExtraDTO dto, int id) {
        Extra extra = extraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Extra not found"));
        extra.setName(dto.getName());
        extra.setDescription(dto.getDescription());
        extra.setPrice(dto.getPrice());
        extra.setExtraType(ExtraType.valueOf(dto.getExtraType()));
        extra = extraRepository.saveAndFlush(extra);
        return new ExtraDTO(extra);
    }

    @Override
    public void deleteMeal(int id) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Meal not found"));
        List<DailyMenu> dailyMenus = new ArrayList<>();
        if (meal instanceof RegularMeal regularMeal) {
            dailyMenus = dailyMenuRepository.findAllByRegular(regularMeal);
            dailyMenus.forEach(dm -> dm.setRegular(null));
            dailyMenuRepository.saveAllAndFlush(dailyMenus);
            regularMealRepository.delete(regularMeal);
        } else if (meal instanceof FitMeal fitMeal) {
            dailyMenus = dailyMenuRepository.findAllByFit(fitMeal);
            dailyMenus.forEach(dm -> dm.setFit(null));
            dailyMenuRepository.saveAllAndFlush(dailyMenus);
            fitMealRepository.delete(fitMeal);
        } else if (meal instanceof Extra extra) {
            if (extra.getExtraType() == ExtraType.SOUP) {
                dailyMenus = dailyMenuRepository.findAllBySoup(extra);
                dailyMenus.forEach(dm -> dm.setSoup(null));
                dailyMenuRepository.saveAllAndFlush(dailyMenus);
            } else if (extra.getExtraType() == ExtraType.DESSERT) {
                dailyMenus = dailyMenuRepository.findAllByDessert(extra);
                dailyMenus.forEach(dm -> dm.setDessert(null));
                dailyMenuRepository.saveAllAndFlush(dailyMenus);
            } else {
                throw new NotFoundException("Extra type not found");
            }
            extraRepository.delete(extra);
        } else {
            throw new NotFoundException("Meal type not found");
        }
    }


}
