package com.sjtxev.sjt_spring_security.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sjtxev.sjt_spring_security.entities.User1;
import com.sjtxev.sjt_spring_security.repositories.User1Repository;
import com.sjtxev.sjt_spring_security.services.User1Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CompanyUsersServiceImplementation implements User1Service {

	@Autowired
	public User1Repository user1Repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User1 users = this.user1Repository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Please enter valid username"));

		return users;

	}

}
