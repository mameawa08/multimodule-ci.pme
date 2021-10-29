package com.administration.repository;

import java.util.Optional;

import com.administration.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByIdentifiant(String id);
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByEmailOrIdentifiant(String email);	
	
	Optional<User> findByResetPasswordToken(String token);	
	

}
