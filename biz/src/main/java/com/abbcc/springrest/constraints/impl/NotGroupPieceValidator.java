package com.abbcc.springrest.constraints.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.abbcc.springrest.constraints.NotGroupPiece;
import com.abbcc.util.constant.group.GroupPiece;

public class NotGroupPieceValidator implements
		ConstraintValidator<NotGroupPiece, String> {

	@Override
	public void initialize(NotGroupPiece constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		try {
			GroupPiece.valueOf(value);
			return false;
		} catch (IllegalArgumentException e) {
			return true;
		}
	}

}
