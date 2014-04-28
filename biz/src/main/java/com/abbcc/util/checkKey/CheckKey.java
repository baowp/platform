package com.abbcc.util.checkKey;

import java.util.List;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcSyscode;
import com.abbcc.service.SyscodeService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.StringUtil;

public class CheckKey {
	public static boolean checkKey(String keyValue) {
		SyscodeService syscodeService = (SyscodeService) BeansFactory
				.get("syscodeService");
		AbcSyscode as = new AbcSyscode();
		as.setType(CommonConst.SYSCODETYPEBANNEDKEY);
		as.setState(CommonConst.STATENORMAL);
		List<AbcSyscode> list = syscodeService.findByExample(as);
		if (list.size() != 0) {
			String[] key = list.get(0).getContent().split(" ");
			for (int i = 0; i < key.length; i++) {
				if (StringUtil.isNotBlank(key[i])) {
					if (keyValue.trim().indexOf(key[i].trim()) > -1) {
						return false;
					}
				}
			}
		}
		return true;
	}
	public static void main(String[] args){
		String key="江浙民";
		String pkey="江浙民/a/m/n枯";
		key.indexOf(pkey);
		pkey.indexOf(key);
	}

}
