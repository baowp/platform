package com.abbcc.util.constant.group;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.persistence.Id;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAlbum;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcSupply;
import com.abbcc.util.AnnotationUtil;
import com.abbcc.util.constant.AlbumType;

public enum GroupStaticDetail {
	PRODUCT(AbcProduct.class, "{state:'" + CommonConst.STATENORMAL
			+ "',isdisplay:'" + CommonConst.PRODUCTDISPLAY + "'}"),

	SUPPLY(AbcSupply.class, "{state:'" + CommonConst.STATENORMAL + "'}"),

	NEWS(AbcNews.class, "{state:'" + CommonConst.STATENORMAL + "',display:'"
			+ CommonConst.NEWSDISPLAY + "'}"),

	ALBUM(AbcAlbum.class, "{state:'" + CommonConst.STATENORMAL
			+ "',blongType:'" + AlbumType.AP.name() + "'}");

	private Class<?> clazz;
	private String example;

	private GroupStaticDetail(Class<?> clazz, String example) {
		this.clazz = clazz;
		this.example = example;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public String getExample() {
		return example;
	}

	/**
	 * @param 实体
	 * @return 该模型类主键值
	 */
	public String getId(Serializable entity) {
		Method method = AnnotationUtil.getAnnotatedMethod(clazz, Id.class);
		try {
			return (String) method.invoke(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
