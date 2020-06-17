package com.hoaxify.ws.configuration;
/*
 * Created by Oray Kurt
 * Date: 10-Jun-20
 * Time: 2:31 PM
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Autowired
	AppConfiguration appConfiguration;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")
				.addResourceLocations("file:./" + appConfiguration.getUploadPath() + "/")
				.setCacheControl(CacheControl.maxAge(30, TimeUnit.DAYS));
	}

	@Bean
	CommandLineRunner createStorageDirectories(){
		return(args -> {
			createFolder(appConfiguration.getUploadPath());
			createFolder(appConfiguration.getProfileStoragePath());
			createFolder(appConfiguration.getAttachmentStoragePath());
		});
	}

	private void createFolder(String path){
		File folder = new File(path);
		boolean folderExist = folder.exists() && folder.isDirectory();
		if(!folderExist){
			folder.mkdir();
		}
	}
}
