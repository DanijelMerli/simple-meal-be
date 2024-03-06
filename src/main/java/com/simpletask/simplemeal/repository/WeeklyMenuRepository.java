package com.simpletask.simplemeal.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpletask.simplemeal.model.WeeklyMenu;

public interface WeeklyMenuRepository extends JpaRepository<WeeklyMenu, Integer>{
	
	
	
	Optional<WeeklyMenu> findByStartDatum(Date datum);

}
