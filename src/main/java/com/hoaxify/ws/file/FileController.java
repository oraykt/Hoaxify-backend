package com.hoaxify.ws.file;
/*
 * Created by Oray Kurt
 * Date: 17-Jun-20
 * Time: 9:04 PM
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

	@Autowired
	FileService fileService;

	@PostMapping("/api/v1/hoax-attachments")
	public FileAttachment saveHoaxAttachment(MultipartFile file){
		return fileService.saveHoaxAttachment(file);
	}
}
