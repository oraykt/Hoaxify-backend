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

	public Page<Hoax> getOldHoaxes(long hoaxId, Pageable page) {

		return hoaxRepository.findByIdLessThan(hoaxId, page);
	}

	public Page<Hoax> getOldHoaxesOfUser(String username, long hoaxId, Pageable page) {
		User inDB = userService.getUser(username);

		return hoaxRepository.findByIdLessThanAndUser(hoaxId, inDB, page);
	}

	public long getNewHoaxesCount(long hoaxId) {
		return  hoaxRepository.countByIdGreaterThan(hoaxId);
	}

	public long getNewHoaxesCountOfUser(String username, long hoaxId) {
		User inDB = userService.getUser(username);

		return hoaxRepository.countByIdGreaterThanAndUser(hoaxId, inDB);
	}

	public List<Hoax> getNewHoaxes(long hoaxId, Sort sort) {

		return hoaxRepository.findByIdGreaterThan(hoaxId, sort);
	}

	public List<Hoax> getNewHoaxesOfUser(String username, long hoaxId, Sort sort) {
		User inDB = userService.getUser(username);

		return hoaxRepository.findByIdGreaterThanAndUser(hoaxId, inDB);
	}
}
