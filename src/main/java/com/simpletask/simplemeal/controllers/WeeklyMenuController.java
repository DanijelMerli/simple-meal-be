package com.simpletask.simplemeal.controllers;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.simpletask.simplemeal.dto.WeeklyMenuAdminDTO;
import com.simpletask.simplemeal.dto.WeeklyMenuDTO;
import com.simpletask.simplemeal.dto.WeeklyMenuResponseDTO;
import com.simpletask.simplemeal.model.WeeklyMenu;
import com.simpletask.simplemeal.repository.DailyMenuRepository;
import com.simpletask.simplemeal.repository.ExtraRepository;
import com.simpletask.simplemeal.repository.FitMealRepository;
import com.simpletask.simplemeal.repository.RegularMealRepository;
import com.simpletask.simplemeal.repository.WeeklyMenuRepository;
import com.simpletask.simplemeal.service.ImageService;
import com.simpletask.simplemeal.service.WeeklyMenuService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/meals/")
public class WeeklyMenuController {
	
	@Autowired
	WeeklyMenuService weekService;
	
	@Autowired
	ImageService imageService;
	
	
	
	@GetMapping("this-week")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<WeeklyMenuDTO> getWeeklyMenu() {
        WeeklyMenuDTO weeklyMenuDTO = weekService.getWeeklyMenuByStartDate();
        if (weeklyMenuDTO != null) {
            return new ResponseEntity<>(weeklyMenuDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	
	@PostMapping("save-weekly-menu")
	public ResponseEntity<WeeklyMenuResponseDTO> saveWeeklyMenu(@RequestBody WeeklyMenuAdminDTO weeklyMenuDTO) throws ParseException, IOException {
		WeeklyMenu weekMenu = weekService.saveWeeklyMenu(weeklyMenuDTO);
		if (weekMenu != null) {
			WeeklyMenuResponseDTO weekMenuResponse = new WeeklyMenuResponseDTO(weekMenu.getId());
            return ResponseEntity.ok(weekMenuResponse);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	}
	
	@PostMapping("uploadFile/{id}")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable("id") int id) {
		WeeklyMenu weekMenu = imageService.addImage(file, id);
		if (weekMenu!=null && weekMenu.getImage()!=null) 
			return new ResponseEntity<>(HttpStatus.OK);
		else 
			return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
		
		@PutMapping("update-weekly-menu")
		public ResponseEntity<String> updateWeeklyMenu(@Valid @RequestBody WeeklyMenuAdminDTO weeklyMenuDTO) throws Exception {
			WeeklyMenu weekMenu = weekService.updateWeeklyMenu(weeklyMenuDTO);
			if (weekMenu != null) {
	            return new ResponseEntity<>(HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		}
}


