package com.simpletask.simplemeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpletask.simplemeal.model.FoodMenu;

public interface FoodMenuRepository extends JpaRepository<FoodMenu, Integer>{

}
