package com.example.hpc.utils;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PagedResult<T> extends PageImpl<T> {

	public PagedResult(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	public PagedResult(List<T> content) {
		super(content);
	}
}
