package com.scoring.services;

import com.scoring.dto.DirigeantDTO;

public interface IMailService {
	
	public void sendNotification(DirigeantDTO dirigeantDTO) throws Exception;

}
