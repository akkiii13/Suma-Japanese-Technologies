package sjtxev.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes().route("your-route-id", r -> r.path("/customers/**", "/carts/**", "/wishlists/**")
				.filters(f -> f.addResponseHeader("Access-Control-Allow-Origin", "http://localhost:3000")
						.addResponseHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
						.addResponseHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Authorization")
						.addResponseHeader("Access-Control-Allow-Credentials", "true"))
				.uri("lb://localhost:6779")).build();
	}

}
