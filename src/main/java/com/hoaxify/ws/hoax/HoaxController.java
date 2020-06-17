package com.hoaxify.ws.hoax;
/*
 * Created by Oray Kurt
 * Date: 12-Jun-20
 * Time: 2:40 PM
 */

import com.hoaxify.ws.hoax.vm.HoaxVM;
import com.hoaxify.ws.shared.CurrentUser;
import com.hoaxify.ws.shared.GenericResponse;
import com.hoaxify.ws.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class HoaxController {

	@Autowired
	HoaxService hoaxService;

	@PostMapping("/hoaxes")
	public GenericResponse saveHoax(@Valid @RequestBody Hoax hoax,
									@CurrentUser User user) {
		hoaxService.save(hoax, user);
		return new GenericResponse("Hoax is saved");
	}

	@GetMapping("/hoaxes")
	public Page<HoaxVM> getHoaxes(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page){
		return hoaxService.getHoaxes(page).map(HoaxVM::new);
	}

	@GetMapping({"/hoaxes/{hoaxId:[0-9]+}", "/users/{username}/hoaxes/{hoaxId:[0-9]+}"})
	public ResponseEntity<?> getHoaxesRelative(@PathVariable long hoaxId,
											   @PathVariable(required = false) String username,
											   @RequestParam(name="count", required = false, defaultValue = "false") boolean count,
											   @RequestParam(name="direction", required = false, defaultValue = "before") String direction,
											   @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page){
		if(count){
			long newHoaxCount = hoaxService.getNewHoaxesCount(hoaxId, username);
			Map<String, Long> response = new HashMap<>();
			response.put("count", newHoaxCount);
			return ResponseEntity.ok(response);
		}
		if(direction.equals("after")){
			List<HoaxVM> newHoaxes = hoaxService.getNewHoaxes(hoaxId, username, page.getSort())
					.stream().map(HoaxVM::new)
					.collect(Collectors.toList());
			return ResponseEntity.ok(newHoaxes);
		}
		return ResponseEntity.ok(hoaxService.getOldHoaxes(hoaxId,username,page).map(HoaxVM::new));
	}


	@GetMapping("/users/{username}/hoaxes")
	public Page<HoaxVM> getUserHoaxes(@PathVariable String username,
									  @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page){
		return hoaxService.getHoaxesOfUser(username, page).map(HoaxVM::new);
	}

}
