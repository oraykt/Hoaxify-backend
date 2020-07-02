package com.hoaxify.ws.file;
/*
 * Created by Oray Kurt
 * Date: 10-Jun-20
 * Time: 2:25 PM
 */

import com.hoaxify.ws.configuration.AppConfiguration;
import org.apache.tika.Tika;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@EnableScheduling
public class FileService {
//	@TODO Logger
//	private static final Logger logger = LoggerFactory.getLogger(FileService.class);

	AppConfiguration appConfiguration;
	Tika tika;
	FileAttachmentRepository fileAttachmentRepository;

	public FileService(AppConfiguration appConfiguration, FileAttachmentRepository fileAttachmentRepository) {
		this.appConfiguration = appConfiguration;
		this.tika = new Tika();
		this.fileAttachmentRepository = fileAttachmentRepository;
	}

	public String writeBase64EncodedStringToFile(String image) throws IOException {
		String fileName = generateRandomName();
		File file = new File(appConfiguration.getProfileStoragePath() + "/" + fileName);
		OutputStream outputStream = new FileOutputStream(file);
		byte[] base64encoded = Base64.getDecoder().decode(image);

		outputStream.write(base64encoded);
		outputStream.close();
		return fileName;
	}

	public String generateRandomName(){
		return UUID.randomUUID().toString().replaceAll("=","");
	}

	public void deleteOldProfile(String oldImageName) {
		if(oldImageName != null){
			deleteFile(Paths.get(appConfiguration.getProfileStoragePath(), oldImageName));
		}
	}

	public String detectType(String base64){
		byte[] base64encoded = Base64.getDecoder().decode(base64);
		return detectType(base64encoded);
	}

	public String detectType(byte[] arr) {
		return tika.detect(arr);
	}

	public FileAttachment saveHoaxAttachment(MultipartFile multipartFile) {
		String fileName = generateRandomName();
		File file = new File(appConfiguration.getAttachmentStoragePath() + "/" + fileName);
		String fileType = null;
		try {

			byte[] fileBytes = multipartFile.getBytes();
			OutputStream outputStream = new FileOutputStream(file);
			outputStream.write(fileBytes);
			outputStream.close();
			fileType = detectType(fileBytes);

		} catch (IOException e) {
			e.printStackTrace();
		}
		FileAttachment attachment = new FileAttachment();
		attachment.setName(fileName);
		attachment.setDate(new Date());
		return fileAttachmentRepository.save(attachment);
	}

	public void deleteAttachment(String oldImageName) {
		if(oldImageName != null){
			deleteFile(Paths.get(appConfiguration.getAttachmentStoragePath(), oldImageName));
		}
	}

	private void deleteFile(Path path){
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Scheduled(fixedRate = 24*60*60*1000)
	public void cleanupStorage(){
		Date twentyFourHoursAgo = new Date(System.currentTimeMillis() - (24*60*60*1000));
		List<FileAttachment> filesToBeDeleted = fileAttachmentRepository.findByDateBeforeAndHoaxIsNull(twentyFourHoursAgo);
		for(FileAttachment file: filesToBeDeleted){
			deleteAttachment(file.getName());
			fileAttachmentRepository.deleteById(file.getId());
		}
	}
}
