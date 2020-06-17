package com.hoaxify.ws.file;
/*
 * Created by Oray Kurt
 * Date: 17-Jun-20
 * Time: 9:41 PM
 */

import com.hoaxify.ws.hoax.Hoax;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class FileAttachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@OneToOne
	private Hoax hoax;
}
