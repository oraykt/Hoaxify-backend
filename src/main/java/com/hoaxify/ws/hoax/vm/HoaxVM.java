package com.hoaxify.ws.hoax.vm;
/*
 * Created by Oray Kurt
 * Date: 12-Jun-20
 * Time: 3:33 PM
 */

import com.hoaxify.ws.hoax.Hoax;
import lombok.Data;

import java.util.Date;

@Data
public class HoaxVM {
	private String content;
	private Date timestamp;

	public HoaxVM(Hoax hoax) {
		this.content = hoax.getContent();
		this.timestamp = hoax.getTimestamp();
	}
}
