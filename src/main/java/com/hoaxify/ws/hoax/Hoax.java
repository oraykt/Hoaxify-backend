package com.hoaxify.ws.hoax;
/*
 * Created by Oray Kurt
 * Date: 12-Jun-20
 * Time: 2:42 PM
 */

import com.hoaxify.ws.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Hoax {

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@Size(min=1, max=500)
	@Lob
	private String content;


	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	private User user;
}
