package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.dto.LoginRequestDTO;
import com.simpletask.simplemeal.dto.LoginResponseDTO;
import com.simpletask.simplemeal.dto.UserDTO;
import com.simpletask.simplemeal.model.User;

public interface IUserService {

	public User registerUser(UserDTO userDTO) throws Exception;
		

	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

	/*public void addDummy();*/
	 
	 
	
	

	
}
