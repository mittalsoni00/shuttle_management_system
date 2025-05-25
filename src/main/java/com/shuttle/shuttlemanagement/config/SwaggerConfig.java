package com.shuttle.shuttlemanagement.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shuttle Management System")
                        .version("1.0")
                        .description("API documentation for the campus shuttle management system made by Mittal Soni")
                        .contact(new Contact()
                                .name("Mittal Soni")
                                .url("https://github.com/mittalsoni00")
                                .email("mittalsoni212@gmail.com")
                        )
                );  }
}

