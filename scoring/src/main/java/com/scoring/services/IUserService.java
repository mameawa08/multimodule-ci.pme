package com.scoring.services;


import com.scoring.config.AccessTokenDetails;
import com.scoring.dto.DemandeScoringDTO;
import com.scoring.dto.UserDTO;
import com.scoring.exceptions.UserException;

import java.util.List;

public interface IUserService {

    public UserDTO addEntrepriseToUser(Long userId, Long entrepriseId) throws UserException;

    AccessTokenDetails getConnectedUser();

    UserDTO getConnectedUserInfos();

    List<UserDTO> getUsersByProfil(Long id) throws UserException;

    UserDTO getUserById(Long idUser);

}
