package com.woniuxy.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		/*registry.addRedirectViewController("pay/savemsg", "/pay/savemsg");*/
		registry.addViewController("/order/page").setViewName("/html/orderpage");
		registry.addViewController("/").setViewName("/html/bookpage");

		registry.addViewController("/orders/all").setViewName("/html/orders");
		registry.addViewController("/one/book").setViewName("/html/bookdetailpage");
		registry.addViewController("/team/book").setViewName("/html/teambookpage");
		registry.addViewController("/backstage/index.html").setViewName("/html/index");

		registry.addViewController("/websocket.html").setViewName("websocket");
		registry.addViewController("/websocket1.html").setViewName("websocket1");

		registry.addViewController("/login/comment").setViewName("/html/comment");


		
	}
	
	
/*	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("../img/**").addResourceLocations("file:D:/hotil/hotel/Hotel/src/main/upload");
	}*/
}
