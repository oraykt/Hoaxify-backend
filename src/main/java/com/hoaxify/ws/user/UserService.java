package com.hoaxify.ws.user;
/*
 * Created by Oray Kurt
 * Date: 19-May-20
 * Time: 12:04 AM
 */

import com.hoaxify.ws.error.NotFoundException;
import com.hoaxify.ws.file.FileService;
import com.hoaxify.ws.user.vm.UserUpdateVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {
//	TODO Logger
//	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	UserRepository userRepository;
	PasswordEncoder passwordEncoder;
	FileService fileService;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, FileService fileService){
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.fileService = fileService;
	}

	public void createUser(User user){
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
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

	public User updateUser(String username, UserUpdateVM updatedUser) {
		User inDB= userRepository.findByUsername(username);
		if(inDB == null){
			throw new NotFoundException();
		}
		inDB.setDisplayName(updatedUser.getDisplayName());

		if(updatedUser.getImage() != null){
			String oldImageName =  inDB.getImage();
			try {
				String storedFile = fileService.writeBase64EncodedStringToFile(updatedUser.getImage());
				inDB.setImage(storedFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			fileService.deleteOldProfile(oldImageName);
		}
		return userRepository.save(inDB);
	}


}
