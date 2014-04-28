package com.abbcc.springrest.api;

import java.util.List;

import com.abbcc.common.PaginationSupport;

public interface ResultsExport<T> {
	List<T> getResultList();

	PaginationSupport<?> getPageList();

	PaginationSupport<T> getPageSupport();
}
