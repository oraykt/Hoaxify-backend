package com.hoaxify.ws.shared;
/*
 * Created by Oray Kurt
 * Date: 11-Jun-20
 * Time: 7:09 PM
 */


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD})
@Retention(RUNTIME)
@Constraint(
		validatedBy = {FileTypeValidator.class}
)
public @interface FileType {
	String message() default "{hoaxify.constraint.FileType.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] types();
}
