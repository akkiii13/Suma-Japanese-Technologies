package com.sjtxev.sjt_spring_security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjtxev.sjt_spring_security.entities.User1;

public interface User1Repository extends JpaRepository<User1, Long> {

	public Optional<User1> findByUsername(String username);

}
