package com.simpletask.simplemeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpletask.simplemeal.model.Meal;

public interface MealRepository extends JpaRepository<Meal, Integer>{

}