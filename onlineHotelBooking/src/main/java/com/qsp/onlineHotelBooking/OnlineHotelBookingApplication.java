package com.qsp.onlineHotelBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
@SpringBootApplication
public class OnlineHotelBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineHotelBookingApplication.class,args);
	}
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("Online Hotel Booking"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiInfoMetaData());
    }

	private ApiInfo apiInfoMetaData() {
	    return new ApiInfoBuilder().title("Job Jugad Application")
	            .description("Application API")
	            .contact(new Contact("Dev-Team", "https://www.dev-team.com/", "dev-team@gmail.com"))
	            .license("Apache 2.0")
	            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	            .version("1.0.0")
	            .build();
	}

}

