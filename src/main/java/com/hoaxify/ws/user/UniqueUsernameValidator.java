package com.hoaxify.ws.user;
/*
 * Created by Oray Kurt
 * Date: 19-May-20
 * Time: 7:30 AM
 */

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	@Autowired
	UserRepository userRepository;

	@Override
	public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
		User user = userRepository.findByUsername(username);
		return user == null;
	}
}
