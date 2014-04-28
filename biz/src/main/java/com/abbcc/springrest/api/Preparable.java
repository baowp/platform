package com.abbcc.springrest.api;

public interface Preparable {
	void prepare() throws Exception;

	void setId(String id);
}
