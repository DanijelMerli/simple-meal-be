package com.simpletask.simplemeal.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.simpletask.simplemeal.dto.DailyMenuDTO;

import com.simpletask.simplemeal.model.DailyMenu;


public class DailyMenuMapper {
	
	private static ModelMapper modelMapper;
	
	@Autowired
    public DailyMenuMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static DailyMenu fromDTOtoDailyMenu(DailyMenuDTO dto) {
        return modelMapper.map(dto, DailyMenu.class);
    }

    public static DailyMenuDTO fromDailyMenuToDTO(DailyMenu dto) {
        return modelMapper.map(dto, DailyMenuDTO.class);
    }

}
