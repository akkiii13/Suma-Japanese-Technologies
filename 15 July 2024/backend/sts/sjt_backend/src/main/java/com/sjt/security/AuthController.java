package com.sjt.security;

import java.security.Principal;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	@Autowired
//	private AuthenticationManager manager;
//
//	@Autowired
//	private JwtHelper helper;
//
//	@Value("${key.location}")
//	private RSAPublicKey key;
//
//	@PostMapping("/login")
//	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
//
//		this.doAuthenticate(request.getMobileNumber(), request.getPassword());
//
//		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getMobileNumber());
//		String token = this.helper.generateToken(userDetails);
//
//		JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//
//	private void doAuthenticate(String username, String password) {
//
//		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
//				password);
//
//		try {
//			manager.authenticate(authentication);
//		} catch (BadCredentialsException e) {
//			throw new BadCredentialsException("Invalid Username or Password!!");
//		} catch (DisabledException e) {
//			throw new DisabledException("User is disabled");
//		}
//
//	}
//
//	@GetMapping("/current-user")
//	public String getLoggedInUser(Principal principal) {
//		return principal.getName();
//	}

}
