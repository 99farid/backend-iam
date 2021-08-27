package com.lawencon.elearning;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.lawencon.elearning.service.MahasiswaService;

@SpringBootApplication
@ComponentScan(basePackages = "com.lawencon")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(MahasiswaService mhs) {
		return args -> {
			try {
				//put your code here for testing				
			} catch (Exception e) {
				e.printStackTrace();
			}

		};
	}
}
