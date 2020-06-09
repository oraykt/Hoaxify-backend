package com.hoaxify.ws.user.vm;
/*
 * Created by Oray Kurt
 * Date: 09-Jun-20
 * Time: 11:08 AM
 */

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserUpdateVM {

	@NotNull
	@Size(min=4,max=255)
	private String displayName;

}
