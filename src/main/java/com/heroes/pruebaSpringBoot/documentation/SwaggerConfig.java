package com.heroes.pruebaSpringBoot.documentation;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	//http://localhost:8080/swagger-ui/index.html#/
	
	@Bean
	public Docket api() {	
	return new Docket(DocumentationType.SWAGGER_2)
			.securitySchemes(Arrays.asList(basicAuthScheme()))
			.securityContexts(Arrays.asList(actuatorSecurityContext()))
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.heroes.pruebaSpringBoot.controller"))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(new ApiInfoBuilder().title("API Super Héroes").description("Gestión de Súper Héroes").version("1.0.0").build());
	}
	
	 private SecurityContext actuatorSecurityContext() {
	        return SecurityContext.builder()
	                .securityReferences(Arrays.asList(basicAuthReference()))
	                .build();
	    }

	    private SecurityScheme basicAuthScheme() {
	        return new BasicAuth("basicAuth");
	    }
	
	 private SecurityReference basicAuthReference() {
	        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
	    }
	
	
	
    
}
