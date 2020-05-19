package com.hoaxify.ws.user;
/*
 * Created by Oray Kurt
 * Date: 18-May-20
 * Time: 10:52 PM
 */

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@Size(min = 4, max = 32)
	@UniqueUsername
	private String username;

	@NotNull
	@Size(min=4,max=255)
	private String displayName;

	@NotNull
	@Size(min=4, max=255)
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{hoaxify.constrain.password.Pattern.message}")
	private String password;
}
