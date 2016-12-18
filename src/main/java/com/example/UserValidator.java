package com.example;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors e) {
		User user = (User) target;
		String userName = user.getUserName();
		String image    = user.getImage();

		if (userName == null || userName.equals("")){
			e.rejectValue("userName", "field.required", "required");
		} else if (!userName.equals("usuario1")) {
		        e.rejectValue("userName", "field.notallowed", "not_allowed");
		}
		
		if (image == null || image.equals("")) {
			e.rejectValue("image", "field.required", "required");
		}
	}
}