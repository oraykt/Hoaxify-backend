package com.hoaxify.ws.user.vm;
/*
 * Created by Oray Kurt
 * Date: 08-Jun-20
 * Time: 8:54 AM
 */

import com.hoaxify.ws.user.User;
import lombok.Data;

@Data
public class UserVM {
	private String username;
	private String displayName;
	private String image;

	public UserVM(User user){
		this.setUsername(user.getUsername());
		this.setDisplayName(user.getDisplayName());
		this.setImage(user.getImage());
	}
}
