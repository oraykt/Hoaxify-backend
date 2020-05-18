package com.hoaxify.ws.user;
/*
 * Created by Oray Kurt
 * Date: 18-May-20
 * Time: 10:52 PM
 */

import lombok.Data;

@Data
public class User {
	private String userName;
	private String displayName;
	private String password;
}
