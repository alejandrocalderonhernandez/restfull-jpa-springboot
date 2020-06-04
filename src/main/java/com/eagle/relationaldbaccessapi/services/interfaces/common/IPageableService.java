package com.eagle.relationaldbaccessapi.services.interfaces.common;

import org.springframework.data.domain.Page;

public interface IPageableService<T> {
	
	public Page<T> getPageByRange(int page, int limit);

}
