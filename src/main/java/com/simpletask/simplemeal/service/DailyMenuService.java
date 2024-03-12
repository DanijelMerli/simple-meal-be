package com.simpletask.simplemeal.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpletask.simplemeal.dto.DailyMenuDTO;
import com.simpletask.simplemeal.model.DailyMenu;
import com.simpletask.simplemeal.repository.DailyMenuRepository;

@Service
public class DailyMenuService {

	@Autowired
	DailyMenuRepository dailyRepo;

	public DailyMenuDTO getDailyMenuForToday() throws Exception {
		Date today = getToday();
		System.out.println(today);

		if (isWeekend()) {
			throw new Exception("Vikend je!");
		}

		Optional<DailyMenu> optionalDailyMenu = dailyRepo.findByDateMenu(today);
		return optionalDailyMenu.map(DailyMenuDTO::new).orElse(null);
	}

	public static boolean isWeekend() {
		Calendar calendar = Calendar.getInstance();
		int danUNedelji = calendar.get(Calendar.DAY_OF_WEEK);
		if (danUNedelji == Calendar.SATURDAY || danUNedelji == Calendar.SUNDAY) {
			System.out.println("Danas je vikend.");
			return true;
		} else {
			System.out.println("Danas je radni dan.");
			return false;
		}
	}

	public static Date getToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public DailyMenuDTO getDailyMenuForTomorrow() throws Exception {
		Date tomorrow = getTomorrow();
		System.out.println(tomorrow);
		if (isWeekend()) {
			throw new Exception("Vikend je!");
		}
		Optional<DailyMenu> optionalDailyMenu = dailyRepo.findByDateMenu(tomorrow);
		return optionalDailyMenu.map(DailyMenuDTO::new).orElse(null);
	}

	public static Date getTomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1; 
		calendar.get(Calendar.YEAR);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

}
