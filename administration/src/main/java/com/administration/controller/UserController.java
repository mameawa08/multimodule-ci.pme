package com.administration.controller;

import java.util.ArrayList;
import java.util.List;

import com.administration.dto.UserDTO;
import com.administration.payload.UserPaylaod;
import com.administration.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/users")
public class UserController {
	
	@Autowired
	private IUserService userService;

	@GetMapping("")
	public ResponseEntity getUsers() {
		try {
			List<UserDTO> users = userService.getUsers();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getUser(Long id) {
		try {
			UserDTO user = userService.getUser(id);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("")
	public ResponseEntity createUser(@RequestBody UserPaylaod paylaod) {
		try {
			UserDTO user = userService.createUser(paylaod);
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/{id}/status", method = {RequestMethod.PATCH, RequestMethod.PUT})
	public ResponseEntity switchStatus(Long id) {
		try {
			boolean status = userService.switchStatus(id);
			return ResponseEntity.status(HttpStatus.CREATED).body(status);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
