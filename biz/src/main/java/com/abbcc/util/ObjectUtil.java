/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "ObjectUtil.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-14           baowp                      initial
 */

package com.abbcc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class ObjectUtil {
	static org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(ObjectUtil.class);

	/**
	 * @author baowp
	 */
	public static void copy(Object source, Object target) {
		extendAll(target, source);
	}

	/**
	 * @author baowp
	 */
	public static Object copy(Object source) {
		Object target = new Object();
		extendAll(target, source);
		return target;
	}

	/**
	 * inherit all properties from parent(s) to self
	 * 
	 * @author baowp
	 * @since 2010-02-02
	 */
	public static void extendAll(Object self, Object... parent) {
		for (Object obj : parent) {
			inheritParent(self, obj, new Build());
		}
	}

	/**
	 * inherit not null properties from parent(s) to self
	 * 
	 * @author baowp
	 * @since 2010-02-02
	 */
	public static void extend(Object self, Object... parent) {
		for (Object obj : parent) {
			inheritParent(self, obj, new Build() {
				public boolean check(Map.Entry<?, ?> entry) {
					return entry.getValue() != null;
				}
			});
		}
	}

	/**
	 * @author baowp
	 * @since 2010-02-02
	 * @return new instance inherit parameters' not null properties
	 */
	@SuppressWarnings("unchecked")
	public static <T> T extendz(T... parent) {
		T newInstance = null;
		try {
			newInstance = (T) parent[0].getClass().newInstance();
		} catch (Exception e) {
		}
		extend(newInstance, parent);
		return newInstance;
	}

	public static void highLight(Object obj) {
		inheritParent(obj, obj, new Build() {
			protected boolean check(Map.Entry<?, ?> entry) {
				return entry.getValue() instanceof String;
			}

			protected Object getValue(Map.Entry<?, ?> entry) {
				return "<span class=\"high-light\">" + entry.getValue()
						+ "</span>";
			}
		});
	}

	public static String getMethodName(String prefix, String fieldName) {
		return prefix + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
	}

	public static String getMethodName(String prefix, Field field) {
		return getMethodName(prefix, field.getName());
	}

	public static String getMethodName(String fieldName) {
		return getMethodName("get", fieldName);
	}

	public static String getMethodName(Field field) {
		return getMethodName("get", field);
	}

	public static String getFieldName(String methodName) {
		return Character.toLowerCase(methodName.charAt(3))
				+ methodName.substring(4);
	}

	// 解决oralce in的时候超出1000条的限制
	public static List<List> getInDataByList(List list) {
		int rv = 0;
		if (list.size() / 200 != 0) {

			if (list.size() / 200 * 200 < list.size()) {
				rv = list.size() / 200 + 1;
			} else {
				rv = list.size() / 200;
			}

		} else {
			rv = 1;
		}
		List al = new ArrayList();
		for (int i = 0; i < rv; i++) {
			al.add(list.subList(i * 200,
					(i + 1) * 200 > list.size() ? list.size() : (i + 1) * 200));
		}
		return al;
	}

	private static void inheritParent(Object self, Object parent, Build build) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Field field : parent.getClass().getFields()) {
			try {
				map.put(field.getName(), field.get(parent));
			} catch (Exception e) {
			}
		}
		for (Method method : parent.getClass().getMethods()) {
			String name = method.getName();
			if (name.startsWith("get") && name.length() > 3
					&& !name.equals("getClass")) {
				try {
					map.put(getFieldName(name), method.invoke(parent));
				} catch (Exception e) {
				}
			}
		}
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (!build.check(entry))
				continue;
			String key = build.getKey(entry);
			Object value = build.getValue(entry);
			String name = getMethodName("set", key);
			try {
				Method method = self.getClass().getMethod(name,
						value.getClass());
				method.invoke(self, value);
			} catch (Exception e) {
				try {
					Field field = self.getClass().getField(key);
					field.set(self, value);
				} catch (Exception e1) {
					try {
						Field field = self.getClass().getDeclaredField(key);
						field.setAccessible(true);
						field.set(self, value);
					} catch (Exception e2) {
					}
				}
			}
		}
	}

	private static class Build {
		protected boolean check(Map.Entry<?, ?> entry) {
			return true;
		}

		protected String getKey(Map.Entry<?, ?> entry) {
			return (String) entry.getKey();
		}

		protected Object getValue(Map.Entry<?, ?> entry) {
			return entry.getValue();
		}
	}

@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void reverse(Map<?, ?> map) {
		Map newMap = new LinkedHashMap();
		Set set = (Set) map.entrySet();
		Entry[] entries = (Entry[]) set.toArray(new Entry[0]);
		for (int i = map.size() - 1; i >= 0; i--) {
			Entry entry = entries[i];
			newMap.put(entry.getKey(), entry.getValue());
		}
		map.clear();
		map.putAll(newMap);
	}
/**
 * 当离线查询中in的个数超过1000时 ，拆分成几个不同的in分句中
 * @param source 满足条件的集合
 * @param dc DetachedCriteria对象
 * @param inOrNot in 或者 not
 * @param field 字段名称
 * @param size 拆分每个in中放多少个
 * @return DetachedCriteria
 */
@SuppressWarnings("unchecked")
public static DetachedCriteria splitSourceInDc(List source,
		DetachedCriteria dc, String inOrNot, String field, int size) {
	if (source != null && source.size() > 0) {
		if (source.size() > size) {
			if (("in").equals(inOrNot)) {
				int k = source.size() / size
						+ (source.size() % size > 0 ? 1 : 0);
				Criterion criterion = null;
				for (int i = 0; i < k; i++) {
					if (i == 0) {
						List sublist = source
								.subList(
										0,
										size > source.size() ? source
												.size() : size);
						criterion = Restrictions.in(field, sublist);
					} else {
						List sublist = source.subList(i * size, (i + 1)
								* size > source.size() ? source.size()
								: (i + 1) * size);
						criterion = Restrictions.or(criterion,
								Restrictions.in(field, sublist));
					}
				}
				dc.add(criterion);
			} else if ("not".equals(inOrNot)) {
				int pageTotal = source.size() / size
						+ (source.size() % size > 0 ? 1 : 0);
				for (int i = 0; i < pageTotal; i++) {
					int start = i * size;
					int end = (i + 1) * size > source.size() ? source
							.size() : (i + 1) * size;
					List sublist = source.subList(start, end);
					dc.add(Restrictions.not(Restrictions.in(field, sublist)));
				}
			}
		} else {
			if (("in").equals(inOrNot)) {
				dc.add(Restrictions.in(field, source));
			} else if ("not".equals(inOrNot)) {
				dc.add(Restrictions.not(Restrictions.in(field, source)));
			}
		}
	}
	return dc;
}

}
