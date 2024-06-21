package com.gd.journal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ServletComponentScan 
@SpringBootApplication
public class JournalApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		// <img alt="" src="/journal/img/${b.fileName}">
		registry.addResourceHandler("/img/**").addResourceLocations("file:///c:/upload/");
	}
	
}
