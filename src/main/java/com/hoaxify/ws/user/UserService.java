package com.hoaxify.ws.user;
/*
 * Created by Oray Kurt
 * Date: 19-May-20
 * Time: 12:04 AM
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	UserRepository userRepository;
	PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public void createUser(User user){
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		logger.info("New User Created!");
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}
}
