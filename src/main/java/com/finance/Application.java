package com.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Configuration
@EnableScheduling
@EnableAutoConfiguration // Sprint Boot Auto Configuration
@ComponentScan(basePackages = "com.finance")
@PropertySource({ "classpath:application.properties", "classpath:message.properties" })
public class Application extends SpringBootServletInitializer {
	private static final Class<Application> applicationClass = Application.class;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	
	
}
