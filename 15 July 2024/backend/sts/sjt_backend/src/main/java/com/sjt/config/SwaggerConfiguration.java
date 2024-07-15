package com.sjt.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

//	private ApiKey apiKeys() {
//		return new ApiKey("JWT", AllConstants.AUTHORIZATION_HEADER, "header");
//	}
//
//	private List<SecurityContext> securityContexts() {
//		return Arrays.asList(SecurityContext.builder().securityReferences(this.securityReferences()).build());
//	}
//
//	private List<SecurityReference> securityReferences() {
//		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] { authorizationScope }));
//	}
//
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).securityContexts(this.securityContexts())
//				.securitySchemes(Arrays.asList(this.apiKeys())).select().apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any()).build();
//	}
//
//	private ApiInfo getInfo() {
//		return new ApiInfo("Company website", "This is build for website", "1.0", "Terms of Service",
//				new Contact("Akshay", "https://sjtev.com", "it1@samjaytrading.com"), "License of APIS",
//				"API License URL", Collections.emptyList());
//	}

}
