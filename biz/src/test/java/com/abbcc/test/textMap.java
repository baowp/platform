package com.abbcc.test;

import java.util.HashMap;
import java.util.Map;

import com.abbcc.util.constant.ModelType;

public class textMap{
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "aa");
		map.put("b", "bb");
		System.out.println(map.get("a"));
		
		System.out.println(ModelType.AD.name());
	}
}
