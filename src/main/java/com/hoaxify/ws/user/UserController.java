package com.hoaxify.ws.user;
/*
 * Created by Oray Kurt
 * Date: 18-May-20
 * Time: 10:52 PM
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping(value = "/api/1.0/users")
	@ResponseStatus(HttpStatus.CREATED)
	public void createUser(@RequestBody User user){
		logger.info("/api/1.0/users");
		try{
			userService.createUser(user);
		}catch(Exception exception){
			return ;
		}

	}
}
