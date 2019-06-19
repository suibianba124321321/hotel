package com.woniuxy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class UploadFileConfiguration {

	@Bean
	public CommonsMultipartResolver commonsMultipartResolver(){
		CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver();
		
		commonsMultipartResolver.setMaxInMemorySize(1024000);
		commonsMultipartResolver.setDefaultEncoding("utf-8");
		commonsMultipartResolver.setResolveLazily(true);
		commonsMultipartResolver.setMaxUploadSize(1024000*20);
		
		return commonsMultipartResolver;
	}
}
