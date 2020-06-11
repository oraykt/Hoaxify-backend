package com.hoaxify.ws.shared;
/*
 * Created by Oray Kurt
 * Date: 11-Jun-20
 * Time: 7:11 PM
 */

import com.hoaxify.ws.file.FileService;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FileTypeValidator implements ConstraintValidator<FileType, String> {

	@Autowired
	FileService fileService;

	String[] types;

	@Override
	public void initialize(FileType constraintAnnotation) {
		this.types =constraintAnnotation.types();
	}

	@Override
	public boolean isValid(String file, ConstraintValidatorContext constraintValidatorContext) {
		if(file == null || file.isEmpty())
			return true;
		String fileType = fileService.detectType(file);

		for(String supportedType: this.types){
			if(fileType.contains(supportedType))
				return true;
		}

		String supportedTypes = Arrays.stream(this.types).collect(Collectors.joining(", "));
		constraintValidatorContext.disableDefaultConstraintViolation();
		HibernateConstraintValidatorContext hibernateConstraintValidatorContext = constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
		hibernateConstraintValidatorContext.addMessageParameter("types", supportedTypes);
		hibernateConstraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate()).addConstraintViolation();

		return false;
	}
}
