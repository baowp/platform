package com.abbcc.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationUtil {

	public static Method getAnnotatedMethod(Class<?> clazz,
			Class<? extends Annotation> annotationClass) {
		for (Method m : clazz.getMethods()) {
			if (m.isAnnotationPresent(annotationClass))
				return m;
		}
		return null;
	}

	public static Field getAnnotatedField(Class<?> clazz,
			Class<? extends Annotation> annotationClass) {
		for (Field f : clazz.getFields()) {
			if (f.isAnnotationPresent(annotationClass))
				return f;
		}
		for (Field f : clazz.getDeclaredFields()) {
			if (f.isAnnotationPresent(annotationClass))
				return f;
		}
		return null;
	}

	public static String getProperty(Class<?> clazz,
			Class<? extends Annotation> annotationClass) {
		Field field = getAnnotatedField(clazz, annotationClass);
		if (field != null)
			return field.getName();
		Method method = getAnnotatedMethod(clazz, annotationClass);
		if (method != null)
			return ObjectUtil.getFieldName(method.getName());
		return null;
	}
}
