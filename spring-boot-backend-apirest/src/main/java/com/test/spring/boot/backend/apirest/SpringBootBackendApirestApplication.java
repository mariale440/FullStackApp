package com.test.spring.boot.backend.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootBackendApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendApirestApplication.class, args);
	}
	

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
 
           registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
 
    }

}
