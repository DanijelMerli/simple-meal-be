package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.dto.UserDTO;
import com.simpletask.simplemeal.model.User;

public interface IUserService {

	public User registerUser(UserDTO userDTO) throws Exception;
	
	

	
}
