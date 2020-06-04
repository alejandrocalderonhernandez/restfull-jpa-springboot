package com.eagle.relationaldbaccessapi.util.util;

import com.eagle.relationaldbaccessapi.util.exceptions.PageabeSizeException;

public class PageableUtil {
	
	private PageableUtil() {
	}
	
	private static final String SMALL = "S";
	private static final String MEDIUM = "M";
	private static final String LARGE = "L";
	private static final String X_LARGE = "XL";
	
	public static int getPageSize(String keyword) throws PageabeSizeException {
		switch (keyword) {
			case SMALL: return 5;
			case MEDIUM: return 10;
			case LARGE: return 15;
			case X_LARGE: return 20;
			default: throw new PageabeSizeException("The keyword " + keyword +" is invalid");
		}
	}
}
