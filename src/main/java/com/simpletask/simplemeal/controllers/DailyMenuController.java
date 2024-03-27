package com.simpletask.simplemeal.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpletask.simplemeal.dto.DailyMenuDTO;
import com.simpletask.simplemeal.service.DailyMenuService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/meals/")
public class DailyMenuController {

	@Autowired
	DailyMenuService dailyService;
	
	
	@GetMapping("/holiday")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_THE_CHOSEN_ONE')")
    public boolean isHoliday() {
        Date date = new Date();
        return dailyService.isHoliday(date);
	}
	
	@GetMapping("/holidayTomorrow") 
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_THE_CHOSEN_ONE')")
    public boolean isHolidayTomorrow() {
		
        return dailyService.isHolidayTomorrow();
	}
        		
	
	
	@GetMapping("/weekend")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_THE_CHOSEN_ONE')")
    public boolean isWeekend() {
	       return  dailyService.isWeekend();
	    }
	
	@GetMapping("/weekendTomorrow")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_THE_CHOSEN_ONE')")
    public boolean isWeekendTomorrow() {
	       return  dailyService.isWeekendTomorrow();
	    }

	@GetMapping("daily-menu/today")
	public ResponseEntity<DailyMenuDTO> getDailyMenuForToday() throws Exception {
		DailyMenuDTO dailyMenuDTO = dailyService.getDailyMenuForToday();
		if (dailyMenuDTO != null) {
			return new ResponseEntity<>(dailyMenuDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("daily-menu/tomorrow")
	public ResponseEntity<DailyMenuDTO> getDailyMenuForTomorrow() throws Exception {
		DailyMenuDTO dailyMenuDTO = dailyService.getDailyMenuForTomorrow();
		if (dailyMenuDTO != null) {
			return new ResponseEntity<>(dailyMenuDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
