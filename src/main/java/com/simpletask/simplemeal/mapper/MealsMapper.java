package com.simpletask.simplemeal.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.simpletask.simplemeal.dto.ExtraDTO;
import com.simpletask.simplemeal.dto.MealsDTO;
import com.simpletask.simplemeal.model.Extra;
import com.simpletask.simplemeal.model.Meal;

public class MealsMapper {
	
	private static ModelMapper modelMapper;
	
	@Autowired
    public MealsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Meal fromDTOtoMeals(MealsDTO dto) {
        return modelMapper.map(dto, Meal.class);
    }

    public static MealsDTO fromMealsToDTO(Meal dto) {
        return modelMapper.map(dto, MealsDTO.class);
    }

}
