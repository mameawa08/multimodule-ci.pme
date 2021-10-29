package com.administration.service;

import java.util.List;

import com.administration.dto.UserDTO;
import com.administration.exception.UserException;
import com.administration.payload.UserPaylaod;

public interface IUserService {
	
	public List<UserDTO> getUsers() throws UserException;
	public UserDTO getUser(Long id) throws UserException;
	public UserDTO createUser(UserPaylaod payload) throws UserException;
	public boolean switchStatus(Long id) throws UserException;
	public UserDTO findUserByEmail(String email);
	public boolean validatePassword(String password);
}
