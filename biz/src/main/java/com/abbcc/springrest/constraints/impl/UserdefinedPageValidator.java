package com.abbcc.springrest.constraints.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.abbcc.springrest.constraints.UserdefinedPage;

public class UserdefinedPageValidator implements
		ConstraintValidator<UserdefinedPage, String> {

	private static String INNER = "[a-z_0-9]+";
	private static String OUTER = "^https?://.+";
	private static Pattern INNER_PATTERN = Pattern.compile(INNER,
			Pattern.CASE_INSENSITIVE);
	private static Pattern OUTER_PATTERN = Pattern.compile(OUTER,
			Pattern.CASE_INSENSITIVE);

	public void initialize(UserdefinedPage constraintAnnotation) {
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.trim().isEmpty())
			return true;
		Matcher m = INNER_PATTERN.matcher(value);
		Matcher m2 = OUTER_PATTERN.matcher(value);
		return m.matches() || m2.matches();
	}

}
