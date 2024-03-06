package com.simpletask.simplemeal.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.simpletask.simplemeal.dto.ExtraDTO;
import com.simpletask.simplemeal.model.Extra;

public class ExtraMapper {
	
	private static ModelMapper modelMapper;
	
		@Autowired
	    public ExtraMapper(ModelMapper modelMapper) {
	        this.modelMapper = modelMapper;
	    }

	    public static Extra fromDTOtoExtra(ExtraDTO dto) {
	        return modelMapper.map(dto, Extra.class);
	    }

	    public static ExtraDTO fromExtraToDTO(Extra dto) {
	        return modelMapper.map(dto, ExtraDTO.class);
	    }

}
