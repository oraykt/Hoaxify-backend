package com.hoaxify.ws.hoax;
/*
 * Created by Oray Kurt
 * Date: 12-Jun-20
 * Time: 2:40 PM
 */

import com.hoaxify.ws.hoax.vm.HoaxVM;
import com.hoaxify.ws.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class HoaxController {

	@Autowired
	HoaxService hoaxService;

	@PostMapping("/hoaxes")
	public GenericResponse saveHoax(@Valid @RequestBody Hoax hoax){
		hoaxService.save(hoax);
		return new GenericResponse("Hoax is saved");
	}

	@GetMapping("/hoaxes")
	public Page<HoaxVM> getHoaxes(Pageable page){
		return hoaxService.getHoaxes(page).map((HoaxVM::new));
	}
}
