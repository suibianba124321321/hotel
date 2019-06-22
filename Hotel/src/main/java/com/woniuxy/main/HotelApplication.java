package com.woniuxy.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.woniuxy.mapper")
@ComponentScan({"com.woniuxy.service",
	"com.woniuxy.controller",
	"com.woniuxy.configuration"
	})
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}
}
