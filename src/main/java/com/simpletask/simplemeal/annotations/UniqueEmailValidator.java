package com.simpletask.simplemeal.annotations;

import org.springframework.beans.factory.annotation.Autowired;

import com.simpletask.simplemeal.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	UserRepository ur;

	private boolean isEmailUnique(String email) {
		if (!ur.existsByEmail(email))
			return true;
		return false;
	}

	@Override
	public void initialize(UniqueEmail constraintAnnotation) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email == null) {
			return true;
		}
		return isEmailUnique(email);
	}
}
