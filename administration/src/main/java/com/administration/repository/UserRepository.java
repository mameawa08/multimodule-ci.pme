package com.administration.repository;

import java.util.List;
import java.util.Optional;

import com.administration.model.Profil;
import com.administration.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String id);

	Optional<User> findByEmail(String email);

	Optional<User> findByEmailOrUsername(String email, String identifiant);

	Optional<User> findByResetPasswordToken(String token);

	Optional<User> findByConfirmationToken(String token);

	List<User> findByProfil(Profil profil);

	Optional<User> findByEmailAndConfirmationToken(String email, String token);

	Optional<User> findByEmailAndActifIsNot(String email, int status);

	Optional<User> findByEmailOrUsernameAndActifIsNot(String email, String identifiant, int status);

	Optional<User> findByEmailAndActifNot(String email, int status);

	Optional<User> findByUsernameAndActifIsNot(String id, int status);
}
