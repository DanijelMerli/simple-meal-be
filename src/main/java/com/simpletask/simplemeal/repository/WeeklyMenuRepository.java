package com.simpletask.simplemeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpletask.simplemeal.model.WeeklyMenu;

public interface WeeklyMenuRepository extends JpaRepository<WeeklyMenu, Integer>{

}
