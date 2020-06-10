package com.hoaxify.ws.user;
/*
 * Created by Oray Kurt
 * Date: 18-May-20
 * Time: 10:52 PM
 */

import com.hoaxify.ws.shared.CurrentUser;
import com.hoaxify.ws.shared.GenericResponse;
import com.hoaxify.ws.user.vm.UserUpdateVM;
import com.hoaxify.ws.user.vm.UserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping(value = "/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user){
		logger.info("/api/v1/users");
		try{
			userService.createUser(user);
			return ResponseEntity.ok(new GenericResponse("User created!"));
		}catch(Exception exception){
			exception.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping(value= "/users")
	public Page<UserVM> getUsers(@CurrentUser User user, Pageable page){
		return userService.getUsers(user, page).map((UserVM::new));
	}

	@GetMapping(value="/users/{username}")
	public UserVM getUser(@PathVariable String username){
		User user = userService.getUser(username);
		return new UserVM(user);
	}

	@PutMapping(value ="/users/{username}")
	@PreAuthorize("#username == principal.username")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateVM updatedUser, @PathVariable String username, @CurrentUser User loggedInUser){
		User user = userService.updateUser(username, updatedUser);
		return ResponseEntity.ok(new UserVM(user));
	}

}
