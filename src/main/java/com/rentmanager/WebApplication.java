package com.rentmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.rentmanager.filter.JwtFilter;
import com.rentmanager.filter.ValidateUserAccessFilter;

/**
 * 
 * @author tanmay
 *
 */
@EnableAutoConfiguration
@ComponentScan
@Configuration
public class WebApplication {
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");

		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean userAuthorizationFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new ValidateUserAccessFilter());
		registrationBean.addUrlPatterns("/api/*");

		return registrationBean;
	}

	public static void main(final String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
	}

}
