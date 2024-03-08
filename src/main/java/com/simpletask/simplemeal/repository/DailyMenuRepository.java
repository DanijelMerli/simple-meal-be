package com.simpletask.simplemeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpletask.simplemeal.model.DailyMenu;

public interface DailyMenuRepository extends JpaRepository<DailyMenu, Integer>{

}
