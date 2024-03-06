package com.simpletask.simplemeal.service;



import java.util.Calendar;
import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpletask.simplemeal.dto.WeeklyMenuDTO;
import com.simpletask.simplemeal.mapper.WeeklyMenuMapper;
import com.simpletask.simplemeal.model.WeeklyMenu;
import com.simpletask.simplemeal.repository.WeeklyMenuRepository;

@Service
public class WeeklyMenuService {
	
	@Autowired
	WeeklyMenuRepository weekRepo;
	
	 public WeeklyMenuDTO getWeeklyMenuByStartDate() {
		 Date startOfWeek = getStartOfWeek();
	     Optional<WeeklyMenu> optionalWeeklyMenu = weekRepo.findByStartDatum(startOfWeek);
	     return optionalWeeklyMenu.map(WeeklyMenuMapper::fromWeeklyMenuToDTO).orElse(null);
	    }
	 
	 public static Date getStartOfWeek() {
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        calendar.set(Calendar.MILLISECOND, 0);
	        return calendar.getTime();
	    }

}
