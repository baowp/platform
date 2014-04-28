package com.abbcc.util;

import java.util.HashMap;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpelFactory extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public Object get(Object key) {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression((String) key);
		Object message = exp.getValue();
		return message;
	}
}
