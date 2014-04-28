package com.abbcc.springrest.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.NotBlank;

import com.abbcc.springrest.constraints.impl.UserdefinedPageValidator;

@Documented
@Constraint(validatedBy = { UserdefinedPageValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@NotBlank
@NotGroupPage
@NotGroupPiece
public @interface UserdefinedPage {
	String message() default "必须由英文字母下划线或数字构成";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
