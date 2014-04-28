package com.abbcc.springrest.constraints.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.abbcc.springrest.constraints.NotGroupPage;
import com.abbcc.util.constant.group.GroupPage;

public class NotGroupPageValidator implements
		ConstraintValidator<NotGroupPage, String> {

	public void initialize(NotGroupPage constraintAnnotation) {
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		return GroupPage.contain(value) ? false : true;
	}
}
