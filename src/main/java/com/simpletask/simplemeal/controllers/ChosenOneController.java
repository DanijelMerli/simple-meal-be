package com.simpletask.simplemeal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpletask.simplemeal.dto.ChosenOneDTO;
import com.simpletask.simplemeal.service.ChosenOneService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/chosen-one")
public class ChosenOneController {

	@Autowired
	ChosenOneService chosenOneService;

	@GetMapping()
	@PreAuthorize("hasRole('ROLE_THE_CHOSEN_ONE')")
	public ResponseEntity<ChosenOneDTO> getDetailsChosenOne() throws Exception {
		ChosenOneDTO chosenOneDTO = chosenOneService.getAllUsers();
		if (chosenOneDTO != null) {
			return new ResponseEntity<>(chosenOneDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/pay/{userId}")
	@PreAuthorize("hasRole('ROLE_THE_CHOSEN_ONE')")
	public boolean markOrderAsPaid(@PathVariable Integer userId) {
		boolean success = chosenOneService.markOrderAsPaid(userId);
		if (success) {
			return true;
		} else {
			return false;
		}
	}
}
