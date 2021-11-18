package com.administration.repository;

import java.util.Optional;

import com.administration.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String id);
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByEmailOrUsername(String email, String identifiant);
	
	Optional<User> findByResetPasswordToken(String token);

	Optional<User> findByConfirmationToken(String token);
	

}
