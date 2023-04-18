package com.ipm_manager_ws.ipm_manager_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.ipm_manager_ws.ipm_manager_app.config.RSAKeyProperties;

@EnableConfigurationProperties(RSAKeyProperties.class)
@ComponentScan
@EnableWebSecurity
@SpringBootApplication
public class IpmManagerAppApplication{

	public static void main(String[] args) {
		SpringApplication.run(IpmManagerAppApplication.class, args);
	}	

}
