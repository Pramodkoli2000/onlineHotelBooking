package com.qsp.onlineHotelBooking;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class SwaggerConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/swagger-ui/")
	        .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/4.14.3/");
	}

}
