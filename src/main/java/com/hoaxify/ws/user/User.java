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


@Data
@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;

	@NotNull
	private String username;

	@NotNull
	private String displayName;

	private String password;
}
