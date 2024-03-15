package com.simpletask.simplemeal.dto;

import java.util.ArrayList;

public class AllMealsDTO {
    private ArrayList<RegularMealDTO> regularMeals;
    private ArrayList<FitMealDTO> fitMeals;
    private ArrayList<ExtraDTO> extras;

    public AllMealsDTO() {
    }

    public AllMealsDTO(ArrayList<RegularMealDTO> regularMeals, ArrayList<FitMealDTO> fitMeals, ArrayList<ExtraDTO> extras) {
        this.regularMeals = regularMeals;
        this.fitMeals = fitMeals;
        this.extras = extras;
    }

    public ArrayList<RegularMealDTO> getRegularMeals() {
        return regularMeals;
    }

    public void setRegularMeals(ArrayList<RegularMealDTO> regularMeals) {
        this.regularMeals = regularMeals;
    }

    public ArrayList<FitMealDTO> getFitMeals() {
        return fitMeals;
    }

    public void setFitMeals(ArrayList<FitMealDTO> fitMeals) {
        this.fitMeals = fitMeals;
    }

    public ArrayList<ExtraDTO> getExtras() {
        return extras;
    }

    public void setExtras(ArrayList<ExtraDTO> extras) {
        this.extras = extras;
    }
}
