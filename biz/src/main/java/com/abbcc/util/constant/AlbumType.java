package com.abbcc.util.constant;

public enum AlbumType {
	AP("用户可见"), BP("用户不可见");
	private String value;

	private AlbumType(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
