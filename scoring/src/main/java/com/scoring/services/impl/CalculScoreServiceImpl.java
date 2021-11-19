package com.scoring.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scoring.dto.DirigeantDTO;
import com.scoring.dto.EntrepriseDTO;
import com.scoring.dto.QuestionDTO;
import com.scoring.dto.ReponseParPMEDTO;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.models.Entreprise;
import com.scoring.models.ReponseParPME;
import com.scoring.payloads.QuestionnaireEliPayload;
import com.scoring.payloads.ReponseParPMEPayload;
import com.scoring.repository.DirigeantRepository;
import com.scoring.repository.EntrepriseRepository;
import com.scoring.repository.QuestionRepository;
import com.scoring.repository.ReponseParPMERepository;
import com.scoring.services.ICalculScoreService;
import com.scoring.services.IMailService;
import com.scoring.services.ITraitementQuestionnaireService;

@Service
public class CalculScoreServiceImpl implements ICalculScoreService {

	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private ReponseParPMERepository reponseParPMERepository;
	
	@Autowired
	private DirigeantRepository dirigeantRepository;

	@Autowired
	private DTOFactory dtoFactory;

	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private IMailService iMailService;
	
	

}
