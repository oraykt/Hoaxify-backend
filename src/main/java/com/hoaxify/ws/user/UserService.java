package com.hoaxify.ws.user;
/*
 * Created by Oray Kurt
 * Date: 19-May-20
 * Time: 12:04 AM
 */

import com.hoaxify.ws.error.NotFoundException;
import com.hoaxify.ws.user.vm.UserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

	public Page<User> getUsers(User user, Pageable page) {
		if(user != null){
			return userRepository.findByUsernameNot(user.getUsername(), page);
		}
		return userRepository.findAll(page);
	}

	public User getUser(String username) {
		User inDB= userRepository.findByUsername(username);
		if(inDB == null){
			throw new NotFoundException();
		}
		return inDB;
	}
}
