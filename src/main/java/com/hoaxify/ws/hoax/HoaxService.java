package com.hoaxify.ws.hoax;
/*
 * Created by Oray Kurt
 * Date: 12-Jun-20
 * Time: 2:44 PM
 */

import com.hoaxify.ws.file.FileAttachment;
import com.hoaxify.ws.file.FileAttachmentRepository;
import com.hoaxify.ws.file.FileService;
import com.hoaxify.ws.hoax.vm.HoaxSubmitVM;
import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HoaxService {
//	@TODO Logger
//	private static final Logger logger = LoggerFactory.getLogger(HoaxService.class);

	HoaxRepository hoaxRepository;
	FileAttachmentRepository fileAttachmentRepository;
	UserService userService;
	FileService fileService;
	public HoaxService(HoaxRepository hoaxRepository, UserService userService, FileAttachmentRepository fileAttachmentRepository, FileService fileService) {
		this.hoaxRepository = hoaxRepository;
		this.userService = userService;
		this.fileAttachmentRepository = fileAttachmentRepository;
		this.fileService = fileService;
	}

	public void save(HoaxSubmitVM hoaxSubmitVM, User user) {
		Hoax hoax = new Hoax();
		hoax.setContent(hoaxSubmitVM.getContent());
		hoax.setTimestamp(new Date());
		hoax.setUser(user);
		hoaxRepository.save(hoax);
		Optional<FileAttachment> optionalFileAttachment = fileAttachmentRepository.findById(hoaxSubmitVM.getAttachmentId());
		if(optionalFileAttachment.isPresent()){
			FileAttachment fileAttachment = optionalFileAttachment.get();
			fileAttachment.setHoax(hoax);
			fileAttachmentRepository.save(fileAttachment);
		}
	}

	public Page<Hoax> getHoaxes(Pageable page) {
		return hoaxRepository.findAll(page);
	}

	public Page<Hoax> getHoaxesOfUser(String username, Pageable page) {
		User inDB = userService.getUser(username);
		return hoaxRepository.findByUser(inDB, page);
	}

	public Page<Hoax> getOldHoaxes(long hoaxId, String username, Pageable page) {
		Specification<Hoax> specification = idLessThan(hoaxId);
		if(username != null){
			User inDB = userService.getUser(username);
			specification = specification.and(userIs(inDB));
		}
		return hoaxRepository.findAll(specification, page);
	}

	public long getNewHoaxesCount(long hoaxId, String username) {
		Specification<Hoax> specification = idGreaterThan(hoaxId);
		if(username != null){
		User inDB = userService.getUser(username);
		specification = specification.and(userIs(inDB));
		}
		return  hoaxRepository.count(specification);
	}

	public List<Hoax> getNewHoaxes(long hoaxId, String username, Sort sort) {
		Specification<Hoax> specification = idGreaterThan(hoaxId);
		if(username != null){
			User inDB = userService.getUser(username);
			specification = specification.and(userIs(inDB));
		}
		return hoaxRepository.findAll(specification, sort);
	}

	Specification<Hoax> idLessThan(long id){
		return (root,criteriaQuery, criteriaBuilder)-> criteriaBuilder.lessThan(root.get("id"), id);
	}

	Specification<Hoax> idGreaterThan(long id){
		return (root,criteriaQuery, criteriaBuilder)-> criteriaBuilder.greaterThan(root.get("id"), id);
	}

	Specification<Hoax> userIs(User user){
		return (root,criteriaQuery, criteriaBuilder)-> criteriaBuilder.equal(root.get("user"), user);
	}

	public void deleteHoax(long hoaxId) {

		Hoax hoaxInDB = hoaxRepository.getOne(hoaxId);

		if(hoaxInDB.getFileAttachment() != null){
			String fileName = hoaxInDB.getFileAttachment().getName();
			fileService.deleteAttachment(fileName);
		}

		hoaxRepository.deleteById(hoaxId);
	}
}
