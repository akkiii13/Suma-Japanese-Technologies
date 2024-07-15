package com.sjtxev.sjt_spring_security.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.sjtxev.sjt_spring_security.services.User1Service;

@Configuration
public class SecurityConfig {

	@Autowired
	public User1Service user1Service;

	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.authorizeHttpRequests(configurer -> configurer.requestMatchers(HttpMethod.POST, "/cart/save/**")
				.hasAnyRole("ADMIN", "EMPLOYEE", "CUSTOMER").requestMatchers(HttpMethod.PUT, "/cart/update/**")
				.hasAnyRole("ADMIN", "EMPLOYEE", "CUSTOMER").requestMatchers(HttpMethod.DELETE, "/cart/delete/**")
				.hasAnyRole("ADMIN", "EMPLOYEE", "CUSTOMER").requestMatchers(HttpMethod.GET, "/cart/get/getAllCarts")
				.hasAnyRole("ADMIN", "EMPLOYEE")
				.requestMatchers(HttpMethod.GET, "/cart/get/getSingleCartByCartIdAndCustomerId/**",
						"/cart/get/getListOfCartsByCustomerId/**")
				.hasAnyRole("ADMIN", "EMPLOYEE", "CUSTOMER").requestMatchers(HttpMethod.POST, "/category/save")
				.hasAnyRole("ADMIN", "EMPLOYEE").requestMatchers(HttpMethod.PUT, "/category/update/**")
				.hasAnyRole("ADMIN", "EMPLOYEE").requestMatchers(HttpMethod.DELETE, "/category/delete/**")
				.hasAnyRole("ADMIN", "EMPLOYEE").requestMatchers(HttpMethod.GET, "/category/get/**")
				.hasAnyRole("ADMIN", "EMPLOYEE"));

		httpSecurity.httpBasic(Customizer.withDefaults());

		httpSecurity.csrf(csrf -> csrf.disable());

		httpSecurity.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));

		return httpSecurity.build();

	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(user1Service);
		authenticationProvider.setPasswordEncoder(this.passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
