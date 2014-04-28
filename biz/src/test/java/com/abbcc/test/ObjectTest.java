package com.abbcc.test;

import org.junit.Test;

import com.abbcc.models.GroupGaim;
import com.abbcc.models.GroupModule;
import com.abbcc.models.annotation.SortProperty;
import com.abbcc.util.AnnotationUtil;
import com.abbcc.util.constant.group.GroupPieceType;

public class ObjectTest {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());

	@Test
	public void t() {
		GroupModule module = new GroupModule();
		module.setType(GroupPieceType.HB);
		module.setMsign("msign");
		GroupModule module2 = module.clone();
		log.info(module2);
		String s = AnnotationUtil.getProperty(GroupGaim.class,
				SortProperty.class);
		log.info(s);
	}
}
