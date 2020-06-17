package com.hoaxify.ws.hoax.vm;
/*
 * Created by Oray Kurt
 * Date: 17-Jun-20
 * Time: 10:46 PM
 */

import com.hoaxify.ws.file.FileAttachment;
import lombok.Data;

@Data
public class FileAttachmentVM {

	private String name;

	public FileAttachmentVM(FileAttachment fileAttachment){
		this.setName(fileAttachment.getName());
	}
}
