package com.simpletask.simplemeal.service;


import java.util.List;
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
	
	 public WeeklyMenuDTO getWeeklyMenuById(int id) {
	        Optional<WeeklyMenu> optionalWeeklyMenu = weekRepo.findById(id);
	        return optionalWeeklyMenu.map(WeeklyMenuMapper::fromWeeklyMenuToDTO).orElse(null);
	    }

}
