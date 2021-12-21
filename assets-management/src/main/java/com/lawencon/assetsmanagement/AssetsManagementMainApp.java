package com.lawencon.assetsmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
@ComponentScan(basePackages = "com.lawencon")
public class AssetsManagementMainApp {
	public static void main(String[] args) {
		SpringApplication.run(AssetsManagementMainApp.class, args);
	}
}
