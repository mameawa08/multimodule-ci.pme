package com.scoring.services;

import com.scoring.dto.DirigeantDTO;
import com.scoring.dto.UserDTO;

public interface IMailService {
	
	public void sendNotification(DirigeantDTO dirigeantDTO, String msg) throws Exception;

    void sendDemandeNotification(UserDTO user, String nomEntreprise) throws Exception;
}
