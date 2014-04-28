package com.abbcc.util.constant;

public enum TailorType {
	/* SELL */
	SE("供应"),
	/* BUY */
	BU("需求"),
	/* AGENT */
	AG("代理"),
	/* COOPERATE */
	CO("合作"),
	/** news */
	NE("新闻"),
	/** product */
	PD("产品");
	private String value;

	private TailorType(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}

}
