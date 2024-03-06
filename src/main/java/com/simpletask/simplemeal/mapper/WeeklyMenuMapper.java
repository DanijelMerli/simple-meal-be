package com.simpletask.simplemeal.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


import com.simpletask.simplemeal.dto.WeeklyMenuDTO;
import com.simpletask.simplemeal.model.WeeklyMenu;

public class WeeklyMenuMapper {
	
private static ModelMapper modelMapper;
	
	@Autowired
    public WeeklyMenuMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static WeeklyMenu fromDTOtoWeeklyMenu(WeeklyMenuDTO dto) {
        return modelMapper.map(dto, WeeklyMenu.class);
    }

    public static WeeklyMenuDTO fromWeeklyMenuToDTO(WeeklyMenu dto) {
        return modelMapper.map(dto, WeeklyMenuDTO.class);
    }

}
