package com.scoring.services;


import com.scoring.config.AccessTokenDetails;
import com.scoring.dto.UserDTO;
import com.scoring.exceptions.UserException;

public interface IUserService {

    public UserDTO addEntrepriseToUser(Long userId, Long entrepriseId) throws UserException;

    AccessTokenDetails getConnectedUser();
}
