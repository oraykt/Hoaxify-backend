package com.hoaxify.ws.auth;
/*
 * Created by Oray Kurt
 * Date: 19-May-20
 * Time: 9:38 PM
 */

import com.fasterxml.jackson.annotation.JsonView;
import com.hoaxify.ws.shared.CurrentUser;
import com.hoaxify.ws.shared.Views;
import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@Autowired
	UserRepository userRepository;


	@PostMapping("/api/v1/auth")
	@JsonView(Views.Base.class)
	private ResponseEntity<?> handleAuthentication(@CurrentUser User user){

		return ResponseEntity.ok(user);
	}

}
