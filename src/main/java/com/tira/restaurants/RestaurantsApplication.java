package com.tira.restaurants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class RestaurantsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantsApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/currentUser").allowedOrigins("http://localhost:4200");
				registry.addMapping("/login").allowedOrigins("http://localhost:4200");
				registry.addMapping("/allRestaurantsSortReservationsToday").allowedOrigins("http://localhost:4200");
				registry.addMapping("/getRestaurantDetails").allowedOrigins("http://localhost:4200");
				registry.addMapping("/logout").allowedOrigins("http://localhost:4200");
				registry.addMapping("/getRestaurantsLocations").allowedOrigins("http://localhost:4200");
				registry.addMapping("/checkReservationAvailability").allowedOrigins("http://localhost:4200");
				
			}
		};
	}
	
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
	    CommonsRequestLoggingFilter crlf = new CommonsRequestLoggingFilter();
	    crlf.setIncludeClientInfo(true);
	    crlf.setIncludeQueryString(true);
	    crlf.setIncludePayload(true);
	    return crlf;
	}
	
}
