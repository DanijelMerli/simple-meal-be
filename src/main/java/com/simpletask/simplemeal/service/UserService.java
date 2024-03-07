package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.dto.LoginRequestDTO;
import com.simpletask.simplemeal.dto.LoginResponseDTO;
import com.simpletask.simplemeal.exception.NotFoundException;
import com.simpletask.simplemeal.model.Role;
import com.simpletask.simplemeal.model.User;
import com.simpletask.simplemeal.repository.RoleRepository;
import com.simpletask.simplemeal.repository.UserRepository;
import com.simpletask.simplemeal.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
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
        User user =  userRepo.findUserByEmail(email);
        if (user != null) {
            String token = jwtTokenUtils.generateToken(email);
//            String refreshToken = jwtTokenUtils.generateRefreshToken(email);
            return new LoginResponseDTO(token);
        } else {
            return null;
        }
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws AuthenticationException, NotFoundException {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword());
        Authentication auth = authenticationManager.authenticate(authReq);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

        String email = loginRequestDTO.getEmail();

        Optional<User> userOptional = userRepo.findByEmail(email);
        User user = userOptional.orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
        return generateTokens(user.getEmail());
    }

    public void addDummy() {
        String pass = passwordEncoder.encode("123");
        System.out.println(pass);
        Optional<Role> opt = roleRepository.findById(1);
        if (opt.isPresent()) {
            User user = new User("Kristina", "A", "andrijinkristina@gmail.com", pass, opt.get());
            userRepo.saveAndFlush(user);
        }
    }
}
