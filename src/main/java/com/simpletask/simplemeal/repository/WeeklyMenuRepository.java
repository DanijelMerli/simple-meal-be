package com.simpletask.simplemeal.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpletask.simplemeal.model.WeeklyMenu;
import org.springframework.data.jpa.repository.Query;

public interface WeeklyMenuRepository extends JpaRepository<WeeklyMenu, Integer>{

	@Query(value = "SELECT * FROM weekly_menu w INNER JOIN daily_menu d on w.id_weekly_menu=d.weekly_menu WHERE start_date BETWEEN ?1 AND ?2", nativeQuery = true)
	Optional<WeeklyMenu> findByStartDate(Date startDate, Date endDate);

}
