package com.nemanjam.ebook.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nemanjam.ebook.entity.UserEntity;
import com.nemanjam.ebook.service.UserService;

public class UsernameUniquenessValidator implements ConstraintValidator<IsUsernameUnique, String> {

	@Autowired
	private UserService userService;
	
	@Override
	public void initialize(IsUsernameUnique isValueUnique) {
		
	}

	@Override
	public boolean isValid(String uniqueValue, ConstraintValidatorContext ctx) {
		
		if (uniqueValue == null || uniqueValue.equals("")) {
			return false;
		}
		
		UserEntity result = (UserEntity) userService.findUserByUsername(uniqueValue);
		
		if (result == null) {
			return true;
		} else {
			return false;
		}
	}
	
}
