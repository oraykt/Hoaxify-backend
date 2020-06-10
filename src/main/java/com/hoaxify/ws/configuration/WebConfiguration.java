package com.hoaxify.ws.configuration;
/*
 * Created by Oray Kurt
 * Date: 10-Jun-20
 * Time: 2:31 PM
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
}
