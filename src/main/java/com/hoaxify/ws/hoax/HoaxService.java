package com.hoaxify.ws.hoax;
/*
 * Created by Oray Kurt
 * Date: 12-Jun-20
 * Time: 2:44 PM
 */

import com.hoaxify.ws.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HoaxService {

	private static final Logger logger = LoggerFactory.getLogger(HoaxService.class);

	HoaxRepository hoaxRepository;

	public HoaxService(HoaxRepository hoaxRepository) {
		this.hoaxRepository = hoaxRepository;
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
}
