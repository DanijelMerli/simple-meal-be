package com.simpletask.simplemeal.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpletask.simplemeal.model.DailyMenu;

public interface DailyMenuRepository extends JpaRepository<DailyMenu, Integer>{

	Optional<DailyMenu> findByDateMenu(Date today);
	

}
