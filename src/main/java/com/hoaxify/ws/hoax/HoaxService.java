package com.hoaxify.ws.hoax;
/*
 * Created by Oray Kurt
 * Date: 12-Jun-20
 * Time: 2:44 PM
 */

import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class HoaxService {

	private static final Logger logger = LoggerFactory.getLogger(HoaxService.class);

	HoaxRepository hoaxRepository;
	UserService userService;

	public HoaxService(HoaxRepository hoaxRepository, UserService userService) {
		this.hoaxRepository = hoaxRepository;
		this.userService = userService;
	}

	public void save(Hoax hoax, User user) {
		hoax.setTimestamp(new Date());
		hoax.setUser(user);
		hoaxRepository.save(hoax);
		logger.info("New Hoax Created!");
	}

	public Page<Hoax> getHoaxes(Pageable page) {
		return hoaxRepository.findAll(page);
	}

	public Page<Hoax> getHoaxesOfUser(String username, Pageable page) {
		User inDB = userService.getUser(username);
		return hoaxRepository.findByUser(inDB, page);
	}

	public Page<Hoax> getOldHoaxes(long hoaxId, String username, Pageable page) {
		if(username != null){
			User inDB = userService.getUser(username);
			return hoaxRepository.findByIdLessThanAndUser(hoaxId, inDB, page);
		}
		return hoaxRepository.findByIdLessThan(hoaxId, page);
	}

	public long getNewHoaxesCount(long hoaxId, String username) {
		if(username != null){
		User inDB = userService.getUser(username);
		return hoaxRepository.countByIdGreaterThanAndUser(hoaxId, inDB);
		}
		return  hoaxRepository.countByIdGreaterThan(hoaxId);
	}

	public List<Hoax> getNewHoaxes(long hoaxId, String username, Sort sort) {
		if(username != null){
			User inDB = userService.getUser(username);
			return hoaxRepository.findByIdGreaterThanAndUser(hoaxId, inDB);
		}
		return hoaxRepository.findByIdGreaterThan(hoaxId, sort);
	}


}
