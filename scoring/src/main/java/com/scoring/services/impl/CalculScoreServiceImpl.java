package com.scoring.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.scoring.exceptions.CalculScoreException;
import com.scoring.models.ReponseParPME;
import com.scoring.repository.ReponseParPMERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scoring.dto.CalibrageDTO;
import com.scoring.dto.EntrepriseDTO;
import com.scoring.dto.IndicateurDTO;
import com.scoring.dto.RatioDTO;
import com.scoring.dto.ScoresAndRatioDTO;
import com.scoring.dto.ScoresParPMEDTO;
import com.scoring.dto.ValeurRatioDTO;
import com.scoring.exceptions.IndicateurException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.models.ScoresParPME;
import com.scoring.models.ValeurRatio;
import com.scoring.repository.EntrepriseRepository;
import com.scoring.repository.ScoreParPMERepository;
import com.scoring.repository.ValeurRatioRepository;
import com.scoring.services.ICalculScoreService;
import com.scoring.services.IIndicateurService;
import com.scoring.services.IReferentielService;


@Service
public class CalculScoreServiceImpl implements ICalculScoreService {

	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private ValeurRatioRepository valeurRatioRepository;
	
	@Autowired
	private ScoreParPMERepository scoreParPMERepository;

	@Autowired
	private DTOFactory dtoFactory;

	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private IIndicateurService indicateurService;
	
	@Autowired
	private IReferentielService referentielService;
	
	@Value("${app.administration.url}")
    private String appUrl;

	@Autowired
	private ReponseParPMERepository reponseParPMERepository;

	public double getRatio1(IndicateurDTO indicateurDTO) throws Exception {
		double somme1, somme2, value;
		somme1 = indicateurDTO.getBkActifCirculant()+indicateurDTO.getBtTresorerieActif();
		somme2=indicateurDTO.getDpPassifCirculant()+indicateurDTO.getDtTresoreriePassif();
		value=somme1/somme2;
		return value;
	}
		
	public double getRatio2(IndicateurDTO indicateurDTO) throws Exception {
		double value;
		value=indicateurDTO.getXiResultatNet()/indicateurDTO.getCaCapitauxPropres();
		return value;
	}

	public double getRatio3(IndicateurDTO indicateurDTO) throws Exception {
		double value;
		value=indicateurDTO.getXiResultatNet()/indicateurDTO.getCaf();
		return value;
	}
		
	public double getRatio4(IndicateurDTO indicateurDTO) throws Exception {
		double value;
		value=indicateurDTO.getCaCapitauxPropres()/indicateurDTO.getDfTotalResources();
		return value;
	}
		
	public double getRatio5(IndicateurDTO indicateurDTO) throws Exception {
		double value;
		value=indicateurDTO.getBiCreanceClient()*360/indicateurDTO.getXbChiffresDaffaires()*1.18;
		return value;
	}
		
	public double getRatio6(IndicateurDTO indicateurDTO) throws Exception {
		double value;
		value=indicateurDTO.getDjDettesFournisseurs()*360/indicateurDTO.getRaAchats()*1.18;
		return value;
	}
		
	public double getRatio7(IndicateurDTO indicateurDTO) throws Exception {
		double value;
		value=indicateurDTO.getXdExcedentBrutExploit()/indicateurDTO.getCaCapitauxPropres();
		return value;
	}
		
	public double getRatio8(IndicateurDTO indicateurDTO) throws Exception {
		double value;
		value=indicateurDTO.getRmChargesFinancieres()/indicateurDTO.getXdExcedentBrutExploit();
		return value;
	}
	
	@Override
	public List<ValeurRatioDTO> calculRatios(Long idEntreprise) throws Exception {
		List<ValeurRatioDTO> listValeurRatio = new ArrayList<ValeurRatioDTO>();
		List<ValeurRatio> listValeur = valeurRatioRepository.findValeurRatioByEntreprise(idEntreprise);
		if(listValeur!=null && !listValeur.isEmpty())
			listValeurRatio = dtoFactory.createListValeurRatio(listValeur);
		else{
			List<RatioDTO> listRatioDTO = referentielService.getlisteRatios();
			IndicateurDTO lastIndicateur = indicateurService.getLastIndicateur(idEntreprise);
			EntrepriseDTO entrepriseDTO = null;
			if(idEntreprise==null)
				throw new IndicateurException("L'id de l'entreprise est obligatoire pour ce calcul !");
			else
				entrepriseDTO = dtoFactory.createEntreprise(entrepriseRepository.findById(idEntreprise).orElseThrow(() -> new Exception("Not found.")));
			for(int i=1; i<=8;i++){
				ValeurRatioDTO valeurRatioDTO = new ValeurRatioDTO();
				valeurRatioDTO.setEntrepriseDTO(entrepriseDTO);
				valeurRatioDTO.setIdRatio(listRatioDTO.get(i).getId());
				if(i==1)
					valeurRatioDTO.setValeur(getRatio1(lastIndicateur));
				else if(i==2)
					valeurRatioDTO.setValeur(getRatio2(lastIndicateur));
				else if(i==3)
					valeurRatioDTO.setValeur(getRatio3(lastIndicateur));
				else if(i==4)
					valeurRatioDTO.setValeur(getRatio4(lastIndicateur));
				else if(i==5)
					valeurRatioDTO.setValeur(getRatio5(lastIndicateur));
				else if(i==6)
					valeurRatioDTO.setValeur(getRatio6(lastIndicateur));
				else if(i==7)
					valeurRatioDTO.setValeur(getRatio7(lastIndicateur));
				else if(i==8)
					valeurRatioDTO.setValeur(getRatio8(lastIndicateur));
				CalibrageDTO calibrageDTO = referentielService.getCalibrageByRatioAndValeurCalcule(valeurRatioDTO.getIdRatio(), valeurRatioDTO.getValeur());
				valeurRatioDTO.setClasse(calibrageDTO.getClasse());
				if(valeurRatioDTO.getClasse()!=0){
					ValeurRatio valeurRatio = modelFactory.createValeurRatio(valeurRatioDTO);
					valeurRatio = valeurRatioRepository.save(valeurRatio);
					listValeurRatio.add(valeurRatioDTO);
				}
			}
		}
		return listValeurRatio;
	}
	
	@Override
	public ScoresAndRatioDTO getScoreFinAndRatios(Long idEntreprise) throws Exception {
		ScoresAndRatioDTO scoreAndratios = new ScoresAndRatioDTO();
		scoreAndratios.setListValeurRatioDTO(calculRatios(idEntreprise));
		ScoresParPMEDTO scoreDTO = new ScoresParPMEDTO();
		ScoresParPME scoreparPME =  scoreParPMERepository.findScoreByEntreprise(idEntreprise);
		if(scoreparPME!=null && scoreparPME.getScore_financier()!=0)
			scoreDTO=dtoFactory.createScoreParPME(scoreparPME);
		else{
			scoreDTO.setEntrepriseDTO(dtoFactory.createEntreprise(entrepriseRepository.findById(idEntreprise).orElseThrow(() -> new Exception("Not found."))));
			double score_financier=0.0;
			Long totalPond=0L;
			for(ValeurRatioDTO valeur : scoreAndratios.getListValeurRatioDTO()){
				RatioDTO ratioDTO = referentielService.getRatioById(valeur.getIdRatio());
				score_financier = score_financier + (valeur.getValeur()*valeur.getClasse()*ratioDTO.getPonderation());
				totalPond = totalPond + ratioDTO.getPonderation();
			}
			scoreDTO.setScore_financier(score_financier/totalPond);
			ScoresParPME score = modelFactory.createScoreParPME(scoreDTO);
			score = scoreParPMERepository.save(score);
		}
		scoreAndratios.setScoreDTO(scoreDTO);
		return scoreAndratios;
	}

	public List calculScoreParametreQualitatif(Long id) throws CalculScoreException {
		List<ReponseParPME> reponseParPMEs = reponseParPMERepository.findAll();
		return null;
	}
	
	
}
