package com.hoaxify.ws.user;
/*
 * Created by Oray Kurt
 * Date: 19-May-20
 * Time: 12:04 AM
 */

import com.hoaxify.ws.shared.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	PasswordEncoder passwordEncoder;

	UserRepository userRepository;
	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	public GenericResponse createUser(User user){
		GenericResponse response = new GenericResponse();
		try{
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			logger.info("New User Created!");
			response.setMessage("User Created!");
			return response;
		}catch(Exception exception){
			logger.error("Unexpected exception in UserService.createUser");
			response.setMessage("Service Error");
			return response;
		}
	}
}
