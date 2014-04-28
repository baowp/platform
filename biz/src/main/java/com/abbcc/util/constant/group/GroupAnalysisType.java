package com.abbcc.util.constant.group;

/**
 * 通信类型
 *
 */
public enum GroupAnalysisType {
	
	CNZZ("中国站长"),
	LA51("51La");
	
	private String value;

	private GroupAnalysisType(String value) {
		this.value = value;
	}
	public String toString() {
		return value;
	}
}
