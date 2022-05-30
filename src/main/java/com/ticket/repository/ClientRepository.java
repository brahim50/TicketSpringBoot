package com.ticket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Client findByEmail(String userEmail);
	
	Client findByNom(String nom);
	

}
