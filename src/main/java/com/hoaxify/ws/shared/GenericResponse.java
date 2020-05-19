package com.hoaxify.ws.shared;
/*
 * Created by Oray Kurt
 * Date: 19-May-20
 * Time: 1:28 AM
 */

import org.springframework.http.HttpStatus;

public class GenericResponse {
	private String message;


	public GenericResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
