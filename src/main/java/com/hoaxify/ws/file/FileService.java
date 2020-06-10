package com.hoaxify.ws.file;
/*
 * Created by Oray Kurt
 * Date: 10-Jun-20
 * Time: 2:25 PM
 */

import com.hoaxify.ws.configuration.AppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileService {

	@Autowired
	AppConfiguration appConfiguration;

	public String writeBase64EncodedStringToFile(String image) throws IOException {
		String fileName = generateRandomName();
		File file = new File(appConfiguration.getUploadPath() + "/" + fileName);
		OutputStream outputStream = new FileOutputStream(file);

		byte[] base64encoded = Base64.getDecoder().decode(image);
		outputStream.write(base64encoded);
		outputStream.close();
		return fileName;
	}

	public String generateRandomName(){
		return UUID.randomUUID().toString().replaceAll("=","");
	}
}
