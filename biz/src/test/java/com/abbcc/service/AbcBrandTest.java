package com.abbcc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.abbcc.models.AbcBrand;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/application*.xml")
@Transactional
public class AbcBrandTest {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());

	@Autowired
	private BrandService brandService;

	@Test
	@Rollback
	public void t() {
		AbcBrand brand = new AbcBrand();
		brand.setBrandId("Brand_00000000000000000000000024");
		brand.setEnterpriseId("ent3");
		brandService.saveOrUpdate(brand);
		log.info(brand.getBrandId());
	}
}
