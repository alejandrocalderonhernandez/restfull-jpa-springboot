package com.eagle.relationaldbaccessapi.util.constants;

public class RestConstants {
	
	private RestConstants() {
	}
	
	public static final String REST_CREATE = "/create";
	public static final String REST_UPDATE = "/update/{id}";
	public static final String REST_FIND_BY_ID = "/find/{id}";
	public static final String REST_FIND_ALL = "/findAll";
	public static final String REST_DELETE = "/delete/{id}";
	
	public static final String REST_PAGE = "/page/{page}/{size}";
	public static final String REST_PAGE_BY_NAME = "/pageByName/{page}/{size}";
	
	public static final String REST_UPLOAD_IMG = "/upload/img/{id}";
	public static final String REST_DELETE_IMG = "/delete/img/{id}";
	public static final String REST_GET_IMG = "/get/img/{id}";
}
