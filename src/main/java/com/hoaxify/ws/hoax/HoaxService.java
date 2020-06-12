package com.hoaxify.ws.hoax;
/*
 * Created by Oray Kurt
 * Date: 12-Jun-20
 * Time: 2:44 PM
 */

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HoaxService {

	HoaxRepository hoaxRepository;

	public HoaxService(HoaxRepository hoaxRepository) {
		this.hoaxRepository = hoaxRepository;
	}

	public void save(Hoax hoax) {
		hoax.setTimestamp(new Date());
		hoaxRepository.save(hoax);
	}
}
