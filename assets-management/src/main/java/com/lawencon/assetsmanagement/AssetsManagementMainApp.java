package com.lawencon.assetsmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.lawencon")
public class AssetsManagementMainApp {
	public static void main(String[] args) {
		SpringApplication.run(AssetsManagementMainApp.class, args);
	}
}
