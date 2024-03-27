package com.simpletask.simplemeal.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpletask.simplemeal.model.WeeklyMenu;
import org.springframework.data.jpa.repository.Query;

public interface WeeklyMenuRepository extends JpaRepository<WeeklyMenu, Integer> {

	Optional<WeeklyMenu> findByStartDateBetween(Date startDate, Date endDate);
	
	Optional<WeeklyMenu> findByStartDate(Date startDate);

	Optional<WeeklyMenu> findById(Integer id);

}
