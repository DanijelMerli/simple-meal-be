package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.dto.LoginRequestDTO;
import com.simpletask.simplemeal.dto.LoginResponseDTO;
import com.simpletask.simplemeal.dto.UserDTO;
import com.simpletask.simplemeal.exception.NotFoundException;
import com.simpletask.simplemeal.model.Role;
import com.simpletask.simplemeal.model.User;
import com.simpletask.simplemeal.repository.RoleRepository;
import com.simpletask.simplemeal.repository.UserRepository;
import com.simpletask.simplemeal.security.TokenUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TokenUtils jwtTokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RoleRepository roleRepository;


	public User findUserByEmail(String email) {
		return userRepo.findUserByEmail(email);
	}

	public LoginResponseDTO generateTokens(String email) {
		User user = userRepo.findUserByEmail(email);
		if (user != null) {
			String token = jwtTokenUtils.generateToken(email);
			return new LoginResponseDTO(token);
		} else {
			return null;
		}
	}

	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws AuthenticationException, NotFoundException {
		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
				loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
		Authentication auth = authenticationManager.authenticate(authReq);
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth);
		String email = loginRequestDTO.getEmail();
		Optional<User> userOptional = userRepo.findByEmail(email);
		User user = userOptional.orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
		return generateTokens(user.getEmail());
	}

	public User registerUser(UserDTO userDTO) throws Exception {
		User user = getUserFromDTO(userDTO);
		if (userRepo.existsByEmail(user.getEmail()))
			return null;
		String password = hashPassword(userDTO.getPassword());
		user.setPassword(password);
		return userRepo.save(user);
	}

	public String hashPassword(String password) {
		return passwordEncoder.encode(password);
	}

	public boolean verifyPassword(String password, String hashedPassword) {
		return passwordEncoder.matches(password, hashedPassword);
	}

	private User getUserFromDTO(UserDTO userDTO) {
		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setRole(roleRepository.findById(1).get());
		return user;
	}

	private UserDTO convertUserToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setEmail(user.getEmail());
		return userDTO;
	}
}
