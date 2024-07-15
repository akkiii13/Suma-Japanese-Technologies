package sjtxev.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.config.CorsRegistry;

import reactor.core.publisher.Mono;

@SpringBootApplication
//@EnableFeignClients
public class ApiGatewayApplication {

	@RequestMapping("/circuitbreakerfallback")
	public String circuitbreakerfallback() {
		return "This is a fallback";
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes().route("path_route", r -> r.path("/get").uri("http://httpbin.org"))
				.route("host_route", r -> r.host("*.myhost.org").uri("http://httpbin.org"))
				.route("rewrite_route",
						r -> r.host("*.rewrite.org").filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
								.uri("http://httpbin.org"))
				.route("circuitbreaker_route",
						r -> r.host("*.circuitbreaker.org").filters(f -> f.circuitBreaker(c -> c.setName("slowcmd")))
								.uri("http://httpbin.org"))
				.route("circuitbreaker_fallback_route",
						r -> r.host("*.circuitbreakerfallback.org")
								.filters(f -> f.circuitBreaker(
										c -> c.setName("slowcmd").setFallbackUri("forward:/circuitbreakerfallback")))
								.uri("http://httpbin.org"))
				.route("limit_route",
						r -> r.host("*.limited.org").and().path("/anything/**")
								.filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
								.uri("http://httpbin.org"))
				.route("websocket_route", r -> r.path("/echo").uri("ws://localhost:9000")).build();
	}

	@Bean
	RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1, 2);
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

//	@Bean
//	public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
//		String httpUri = uriConfiguration.getHttpbin();
//		return builder.routes()
//				.route(p -> p.path("/get").filters(f -> f.addRequestHeader("Hello", "World")).uri(httpUri))
//				.route(p -> p.host("*.circuitbreaker.com").filters(
//						f -> f.circuitBreaker(config -> config.setName("mycmd").setFallbackUri("forward:/fallback")))
//						.uri(httpUri))
//				.build();
//	}

//	@RequestMapping("/fallback")
//	public Mono<String> fallback() {
//		return Mono.just("fallback");
//	}

//	@Bean
//	WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
//			}
//		};
//	}

//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes().route("your-route-id", r -> r.path("/customers/**", "/carts/**", "/wishlists/**")
//				.filters(f -> f.addResponseHeader("Access-Control-Allow-Origin", "http://localhost:3000")
//						.addResponseHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
//						.addResponseHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Authorization")
//						.addResponseHeader("Access-Control-Allow-Credentials", "true"))
//				.uri("lb://localhost:6779")).build();
//	}

}

//@ConfigurationProperties
//class UriConfiguration {
//
//	private String httpbin = "http://httpbin.org:80";
//
//	public String getHttpbin() {
//		return httpbin;
//	}
//
//	public void setHttpbin(String httpbin) {
//		this.httpbin = httpbin;
//	}
//}