package com.simpletask.simplemeal.services;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.util.Base64;

import javax.crypto.Cipher;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpletask.simplemeal.model.User;
import com.simpletask.simplemeal.repository.UserRepository;

import dto.UserDTO;

@Service
public class UserService implements UserServiceI{
	
	private UserRepository ur;
	private  ModelMapper modelMapper;
	
	@Autowired
	public UserService(UserRepository ur,ModelMapper modelMapper) {
		this.ur = ur;
		this.modelMapper=modelMapper;
	}

	public UserService() {}


	public User registerUser(UserDTO userDTO) throws Exception  {
		User user = convertToUser(userDTO);
		String encPassword = encryptPassword(user.getPassword());
		user.setPassword(encPassword);
		return ur.save(user);
	} 
	
	private String encryptPassword(String password) throws Exception {
	    
	    String encryptedPassword = asymmetricEncrypt(password);
	    return encryptedPassword;
	}
	
    public  String asymmetricEncrypt(String password) throws Exception {
        KeyPair keyPair = generateRSAKeyPair();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private  KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); 
        return keyPairGenerator.generateKeyPair();
    }

    private   PrivateKey getPrivateKey(KeyPair keyPair) {
        return keyPair.getPrivate();
    }
    
    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    
    public User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
    
    
   

}
