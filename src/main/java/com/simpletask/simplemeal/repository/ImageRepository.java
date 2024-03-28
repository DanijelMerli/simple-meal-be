package com.simpletask.simplemeal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpletask.simplemeal.model.Image;
import com.simpletask.simplemeal.model.WeeklyMenu;

public interface ImageRepository extends JpaRepository<Image,Long> {
	
	public Optional<Image> findByWeeklyMenu(WeeklyMenu weeklyMenu);

	Optional<Image> findById(int id);
	
}
