package com.simpletask.simplemeal.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.simpletask.simplemeal.model.Extra;
import com.simpletask.simplemeal.model.FitMeal;
import com.simpletask.simplemeal.model.RegularMeal;
import org.springframework.data.jpa.repository.JpaRepository;

import com.simpletask.simplemeal.model.DailyMenu;

public interface DailyMenuRepository extends JpaRepository<DailyMenu, Integer> {

	Optional<DailyMenu> findByDateMenu(Date today);

	List<DailyMenu> findAllByRegular(RegularMeal regularMeal);

	List<DailyMenu> findAllByFit(FitMeal fitMeal);

	List<DailyMenu> findAllBySoup(Extra soup);

	List<DailyMenu> findAllByDessert(Extra dessert);
}
