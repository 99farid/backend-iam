package com.lawencon.assetsmanagement.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lawencon.assetsmanagement.util.TemplateEmailUtil;
import com.lawencon.util.ExcelUtil;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class ObjectConfig {
//	@Bean
//	public SpringLiquibase initTabel(@Autowired DataSource dataSource) {
//		SpringLiquibase liquibase = new SpringLiquibase();
//		liquibase.setDataSource(dataSource);
//
//		liquibase.setChangeLog("/db/init_table_and_data.sql");
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
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public TemplateEmailUtil templateEmailUtil() {
		return new TemplateEmailUtil();
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
	
	@Bean
	public Executor executor() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		DelegatingSecurityContextExecutor delegatingExecutorCustom = new DelegatingSecurityContextExecutor(
				executorService);
		return delegatingExecutorCustom;
	}
}
