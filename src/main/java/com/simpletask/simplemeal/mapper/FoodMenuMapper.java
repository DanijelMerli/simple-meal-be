package com.simpletask.simplemeal.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.simpletask.simplemeal.dto.ListOfMealsDTO;
import com.simpletask.simplemeal.model.Meal;

public class FoodMenuMapper {
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static ListOfMealsDTO toDTO(Meal meal) {
        return modelMapper.map(meal, ListOfMealsDTO.class);
    }

    public static List<ListOfMealsDTO> toDTOList(List<Meal> meals) {
        return meals.stream()
                .map(meal -> toDTO(meal))
                .collect(Collectors.toList());
    }


}
