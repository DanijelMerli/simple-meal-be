package com.simpletask.simplemeal.services;

import org.springframework.stereotype.Service;

import com.simpletask.simplemeal.dto.UserDTO;
import com.simpletask.simplemeal.model.User;

public interface UserServiceI {

	public User registerUser(UserDTO userDTO) throws Exception;
	
	public UserDTO convertToDTO(User user);
	
	public User convertToUser(UserDTO userDTO);
	 
	 
	
}
