package com.scoring.services;


import com.scoring.config.AccessTokenDetails;
import com.scoring.dto.UserDTO;
import com.scoring.exceptions.UserException;

import java.util.List;

public interface IUserService {

    public UserDTO addEntrepriseToUser(Long userId, Long entrepriseId) throws UserException;

    AccessTokenDetails getConnectedUser();

    List<UserDTO> getUsersByProfil(Long id) throws UserException;
}
