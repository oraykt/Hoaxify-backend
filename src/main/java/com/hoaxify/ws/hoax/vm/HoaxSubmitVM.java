package com.hoaxify.ws.hoax.vm;
/*
 * Created by Oray Kurt
 * Date: 17-Jun-20
 * Time: 10:23 PM
 */

import lombok.Data;

import javax.validation.constraints.Size;


@Data
public class HoaxSubmitVM {

	@Size(min=1, max=1000)
	private String content;

	private long attachmentId;
}
