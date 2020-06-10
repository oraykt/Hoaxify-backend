package com.hoaxify.ws.configuration;
/*
 * Created by Oray Kurt
 * Date: 10-Jun-20
 * Time: 3:17 PM
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "hoaxify")
public class AppConfiguration {

	private String uploadPath;
}
