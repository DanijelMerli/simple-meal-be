package com.simpletask.simplemeal.service;



import java.util.Calendar;
import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpletask.simplemeal.dto.WeeklyMenuDTO;
import com.simpletask.simplemeal.model.WeeklyMenu;
import com.simpletask.simplemeal.repository.WeeklyMenuRepository;

@Service
public class WeeklyMenuService {
	
	@Autowired
	WeeklyMenuRepository weekRepo;
	
	 public WeeklyMenuDTO getWeeklyMenuByStartDate() {
		 Date startOfWeek = getStartOfWeek();
		 Date endOfWeek = getEndOfWeek(startOfWeek);
		 System.out.println(startOfWeek);
		 System.out.println(endOfWeek);
	     Optional<WeeklyMenu> optionalWeeklyMenu = weekRepo.findByStartDate(startOfWeek, endOfWeek);
		 return optionalWeeklyMenu.map(WeeklyMenuDTO::new).orElse(null);
	 }

	public static Date getStartOfWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getEndOfWeek(Date start) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		calendar.add(Calendar.DAY_OF_WEEK, 6);
		return calendar.getTime();
	}

}