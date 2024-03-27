package com.simpletask.simplemeal.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
		if (isWeekend()) {
			throw new Exception("Vikend je!");
		} else if (isHoliday(today)) {
			throw new Exception("Praznik je!");
		}
		Optional<DailyMenu> optionalDailyMenu = dailyRepo.findByDateMenu(today);
		return optionalDailyMenu.map(DailyMenuDTO::new).orElse(null);
	}

	public boolean isWeekend() {
		Calendar calendar = Calendar.getInstance();
		int danUNedelji = calendar.get(Calendar.DAY_OF_WEEK);
		if (danUNedelji == Calendar.SATURDAY || danUNedelji == Calendar.SUNDAY) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isWeekendTomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		int danUNedelji = calendar.get(Calendar.DAY_OF_WEEK);
		if (danUNedelji == Calendar.SATURDAY || danUNedelji == Calendar.SUNDAY) {
			return true;
		} else {
			return false;
		}
	}

	public Date getToday() {
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
		if (isWeekendTomorrow()) {
			throw new Exception("Vikend je!");
		} else if (isHoliday(tomorrow)) {
			throw new Exception("Praznik je!");
		}
		Optional<DailyMenu> optionalDailyMenu = dailyRepo.findByDateMenu(tomorrow);
		return optionalDailyMenu.map(DailyMenuDTO::new).orElse(null);
	}

	public Date getTomorrow() {
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

	public boolean isHoliday(Date date) {
		Set<Date> praznici = new HashSet<>();
		praznici.add(new Date(124, 0, 1));
		praznici.add(new Date(124, 0, 2));
		praznici.add(new Date(124, 0, 6));
		praznici.add(new Date(124, 0, 7));
		praznici.add(new Date(124, 1, 15));
		praznici.add(new Date(124, 4, 1));
		praznici.add(new Date(124, 10, 11));
		if (praznici.contains(date)) {
			return true;
		} else {
			return false;
		}
	}
}
