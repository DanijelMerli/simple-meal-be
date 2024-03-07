package com.simpletask.simplemeal.services;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.simpletask.simplemeal.dto.UserDTO;
import com.simpletask.simplemeal.exception.InvalidRegisterException;
import com.simpletask.simplemeal.model.User;
import com.simpletask.simplemeal.repository.UserRepository;

@Service
public class UserService implements UserServiceI{
	
	private UserRepository ur;
	private  ModelMapper modelMapper;
	private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	@Autowired
	public UserService(UserRepository ur,ModelMapper modelMapper) {
		this.ur = ur;
		this.modelMapper=modelMapper;
	}

	public UserService() {} 


	public User registerUser(UserDTO userDTO) throws Exception  {
		
		User user = convertToUser(userDTO);
		if (ur.existsByEmail(user.getEmail()))
			return null;
		String password =hashPassword(userDTO.getPassword());
		user.setPassword(password);
		return ur.save(user);
	} 
	
	public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean verifyPassword(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }
	
    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    
    public User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
    
    
   

}
