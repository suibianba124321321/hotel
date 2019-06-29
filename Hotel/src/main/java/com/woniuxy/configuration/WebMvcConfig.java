package com.woniuxy.configuration;

import org.springframework.context.annotation.Configuration;
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
		registry.addViewController("/websocket.html").setViewName("websocket");
		registry.addViewController("/websocket1.html").setViewName("websocket1");

		
	}
}
