package com.lawencon.assetsmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lawencon.util.ExcelUtil;

@Configuration
public class ObjectConfig {
//	@Bean
//	public SpringLiquibase initTabel(@Autowired DataSource dataSource) {
//		SpringLiquibase liquibase = new SpringLiquibase();
//		liquibase.setDataSource(dataSource);
//
//		liquibase.setChangeLog("/db/migration/script/init_db.sql");
//		return liquibase;
//
//	}

	@Bean
	public BCryptPasswordEncoder bcriptEndcoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ExcelUtil excelUtil() {
		return new ExcelUtil();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods(HttpMethod.POST.name(),
						HttpMethod.GET.name(), HttpMethod.PATCH.name(), HttpMethod.DELETE.name(),
						HttpMethod.PUT.name());
			}
		};
	}
}
