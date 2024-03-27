package com.simpletask.simplemeal.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumValuesValidation implements ConstraintValidator<EnumValues, String> {
	private String[] allowedValues;
	private boolean nullable;

	@Override
	public void initialize(EnumValues constraintAnnotation) {
		this.allowedValues = constraintAnnotation.values();
		this.nullable = constraintAnnotation.nullable();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		if (value == null) {
			return nullable;
		}
		return Arrays.asList(allowedValues).contains(value);
	}
}
