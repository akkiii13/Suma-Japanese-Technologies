package com.trial1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trial1.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}