package com.hoaxify.ws.hoax;
/*
 * Created by Oray Kurt
 * Date: 07-Jul-2020
 * Time: 17:45
 */

import com.hoaxify.ws.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HoaxSecurityService {

	@Autowired
	HoaxRepository hoaxRepository;

	public boolean isAllowedToDelete(long hoaxId, User loggedInUser){
		Optional<Hoax> optionalHoax = hoaxRepository.findById(hoaxId);
		if(!optionalHoax.isPresent()) {
			return false;
		}
		Hoax hoax = optionalHoax.get();
		if(hoax.getUser().getId() != loggedInUser.getId()){
			return false;
		}
		return true;
	}
}
