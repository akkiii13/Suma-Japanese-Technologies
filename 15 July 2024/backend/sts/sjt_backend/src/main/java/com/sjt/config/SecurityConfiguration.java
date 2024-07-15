package com.sjt.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//import com.sjt.security.JwtAuthenticationEntryPoint;

@Configuration
//@EnableWebSecurity
@EnableWebMvc
//@EnableMethodSecurity
public class SecurityConfiguration {

//	@Autowired
//	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//	@Autowired
//	private DaoAuthenticationProvider daoAuthenticationProvider;
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//		http.httpBasic(Customizer.withDefaults());
//
//		http.csrf(csrf -> csrf.disable());
//
//		http.authorizeHttpRequests(i -> i.requestMatchers(AllConstants.PUBLIC_URLS).permitAll()
//				.requestMatchers("/auth/create-user").permitAll().anyRequest().authenticated());
//
//		http.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint));
//
//		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//		http.oauth2ResourceServer(configure -> configure.jwt(Customizer.withDefaults()));
//
//		http.formLogin(login -> login.loginPage("/signin").usernameParameter("username").passwordParameter("password")
//				.defaultSuccessUrl("/").failureUrl("/signin").permitAll());
//
//		http.logout(logout -> logout.logoutUrl("/signout").permitAll());
//
//		http.cors(cors -> cors.configurationSource(req -> {
//			CorsConfiguration corsConfiguration = new CorsConfiguration();
//			corsConfiguration.setAllowedMethods(List.of("*"));
//			return corsConfiguration;
//		}));
//
////		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//		http.authenticationProvider(this.daoAuthenticationProvider);
//
//		DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
//
//		return defaultSecurityFilterChain;
//
//	}

}
