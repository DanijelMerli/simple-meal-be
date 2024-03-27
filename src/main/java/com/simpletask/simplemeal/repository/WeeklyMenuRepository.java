package com.simpletask.simplemeal.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpletask.simplemeal.model.WeeklyMenu;
import org.springframework.data.jpa.repository.Query;

public interface WeeklyMenuRepository extends JpaRepository<WeeklyMenu, Integer>{

//	@Query(value = "SELECT start_date, dessert, fit, regular, soup, weekly_menu, date_menu, d.id FROM weekly_menus w INNER JOIN daily_menus d on w.id=d.weekly_menu WHERE start_date BETWEEN ?1 AND ?2", nativeQuery = true)
	Optional<WeeklyMenu> findByStartDateBetween(Date startDate, Date endDate);

	Optional<WeeklyMenu> findById(Integer id);

}
