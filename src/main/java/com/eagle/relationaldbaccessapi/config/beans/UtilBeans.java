package com.eagle.relationaldbaccessapi.config.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class UtilBeans {
	
	@Bean
	@Scope("prototype")
	public StringBuilder getStringBuilder() {
		return new StringBuilder();
	}
	
}
