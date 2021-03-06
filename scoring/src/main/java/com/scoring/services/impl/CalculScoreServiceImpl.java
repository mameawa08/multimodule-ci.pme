package com.scoring.services.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scoring.dto.CalibrageDTO;
import com.scoring.dto.DemandeScoringDTO;
import com.scoring.dto.EntrepriseDTO;
import com.scoring.dto.IndicateurDTO;
import com.scoring.dto.ParametreDTO;
import com.scoring.dto.PonderationDTO;
import com.scoring.dto.RatioDTO;
import com.scoring.dto.ScoreEntrepriseParParametreDTO;
import com.scoring.dto.ScoresAndRatioDTO;
import com.scoring.dto.ScoresParPMEDTO;
import com.scoring.dto.ValeurRatioDTO;
import com.scoring.exceptions.CalculScoreException;
import com.scoring.exceptions.IndicateurException;
import com.scoring.exceptions.ScoreEntrepriseParParametreException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.models.DemandeScoring;
import com.scoring.models.Entreprise;
import com.scoring.models.Parametre;
import com.scoring.models.Question;
import com.scoring.models.ReponseParPME;
import com.scoring.models.ReponseQualitative;
import com.scoring.models.ScoresParPME;
import com.scoring.models.ValeurRatio;
import com.scoring.repository.DemandeScoringRepository;
import com.scoring.repository.EntrepriseRepository;
import com.scoring.repository.ParametreRepository;
import com.scoring.repository.QuestionRepository;
import com.scoring.repository.ReponseParPMERepository;
import com.scoring.repository.ReponseQualitativeRepository;
import com.scoring.repository.ScoreParPMERepository;
import com.scoring.repository.ValeurRatioRepository;
import com.scoring.services.ICalculScoreService;
import com.scoring.services.IDemandeScoring;
import com.scoring.services.IEntrepriseService;
import com.scoring.services.IIndicateurService;
import com.scoring.services.IReferentielService;
import com.scoring.services.IScoreEntrepriseParParametreService;
import com.scoring.utils.Constante;


@Service
public class CalculScoreServiceImpl implements ICalculScoreService {

	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private ValeurRatioRepository valeurRatioRepository;
	
	@Autowired
	private ScoreParPMERepository scoreParPMERepository;
	
	@Autowired
	private DemandeScoringRepository demandeScoringRepository;

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

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ReponseQualitativeRepository reponseQualitativeRepository;

	@Autowired
	private ParametreRepository parametreRepository;

	@Autowired
	private IScoreEntrepriseParParametreService scoreEntrepriseParParametreService;

	@Autowired
	private IEntrepriseService entrepriseService;
	
	@Autowired
	private IDemandeScoring demandeScoringService;


	public double getRatio1(IndicateurDTO indicateurDTO) {
		double somme1, somme2;
		somme1 = indicateurDTO.getBkActifCirculant() + indicateurDTO.getBtTresorerieActif();
		somme2 = indicateurDTO.getDpPassifCirculant() + indicateurDTO.getDtTresoreriePassif();
		if(somme1 == 0 || somme2 == 0){
			return 0;
		}
		return somme1 / somme2;
	}
		
	public double getRatio2(IndicateurDTO indicateurDTO)  {
		if(indicateurDTO.getXiResultatNet() == 0 || indicateurDTO.getCaCapitauxPropres() == 0){
			return 0;
		}
		return indicateurDTO.getXiResultatNet() / indicateurDTO.getCaCapitauxPropres();
	}

	public double getRatio3(IndicateurDTO indicateurDTO)  {
		if(indicateurDTO.getEndettement_struct() == 0 || indicateurDTO.getCaf() == 0){
			return 0;
		}
		return indicateurDTO.getEndettement_struct() / indicateurDTO.getCaf();
	}
		
	public double getRatio4(IndicateurDTO indicateurDTO)  {
		if(indicateurDTO.getCaCapitauxPropres() == 0 || indicateurDTO.getDfTotalResources() == 0){
			return 0;
		}
		return indicateurDTO.getCaCapitauxPropres()/indicateurDTO.getDfTotalResources();
	}
		
	public double getRatio5(IndicateurDTO indicateurDTO)  {
		if(indicateurDTO.getBiCreanceClient() == 0 || indicateurDTO.getXbChiffresDaffaires() == 0){
			return 0;
		}
		return (indicateurDTO.getBiCreanceClient()*360) / (indicateurDTO.getXbChiffresDaffaires()*1.18);
	}
		
	public double getRatio6(IndicateurDTO indicateurDTO)  {
		if(indicateurDTO.getDjDettesFournisseurs() == 0 || indicateurDTO.getRaAchats() == 0){
			return 0;
		}
		return (indicateurDTO.getDjDettesFournisseurs()*360) / (indicateurDTO.getRaAchats()*1.18);
	}
		
	public double getRatio7(IndicateurDTO indicateurDTO) {
		if(indicateurDTO.getXdExcedentBrutExploit() == 0 || indicateurDTO.getCaCapitauxPropres() == 0){
			return 0;
		}
		return indicateurDTO.getXdExcedentBrutExploit()/indicateurDTO.getCaCapitauxPropres();
	}
		
	public double getRatio8(IndicateurDTO indicateurDTO) {
		if (indicateurDTO.getRmChargesFinancieres() == 0 || indicateurDTO.getXdExcedentBrutExploit() == 0){
			return 0;
		}
		return indicateurDTO.getRmChargesFinancieres()/indicateurDTO.getXdExcedentBrutExploit();
	}
	
	@Override
	public List<ValeurRatioDTO> calculRatios(Long idDemande) throws Exception {
		List<ValeurRatioDTO> listValeurRatio = new ArrayList<ValeurRatioDTO>();
		if(idDemande==null){
			throw new IndicateurException("L'id de la demande est obligatoire pour ce calcul !");
		}
		//get demande scoring
		DemandeScoringDTO demande = demande = demandeScoringService.getDemande(idDemande);
		//get liste valeur ratio existant
		List<ValeurRatio> listValeur = valeurRatioRepository.findValeurRatioByDemande(idDemande);
		listValeur = listValeur.stream().sorted(Comparator.comparingLong(ValeurRatio::getIdRatio)).collect(Collectors.toList());
//		get liste ratio
		List<RatioDTO> listRatioDTO = referentielService.getlisteRatios();
		listRatioDTO = listRatioDTO.stream().sorted(Comparator.comparingLong(RatioDTO::getId)).collect(Collectors.toList());
		//get indicateur de la dernier annee
		IndicateurDTO lastIndicateur = indicateurService.getLastIndicateur(idDemande);

		for(int i=1; i<=8;i++){
			ValeurRatioDTO valeurRatioDTO;
			if(listValeur.size() != 0 && listValeur.get(i - 1) != null){
				valeurRatioDTO = dtoFactory.createValeurRatio(listValeur.get(i - 1));
			}
			else {
				valeurRatioDTO = new ValeurRatioDTO();
				valeurRatioDTO.setDemandeScoringDTO(demande);
				valeurRatioDTO.setIdRatio(listRatioDTO.get(i - 1).getId());
			}
			RatioDTO ratioDTO = referentielService.getRatioById(valeurRatioDTO.getIdRatio());
			valeurRatioDTO.setNomRatio(ratioDTO.getLibelle());
			valeurRatioDTO.setCodeRatio(ratioDTO.getCode());
			if(i==1)
				valeurRatioDTO.setValeur(BigDecimal.valueOf(getRatio1(lastIndicateur)));
			else if(i==2)
				valeurRatioDTO.setValeur(BigDecimal.valueOf(getRatio2(lastIndicateur)));
			else if(i==3)
				valeurRatioDTO.setValeur(BigDecimal.valueOf(getRatio3(lastIndicateur)));
			else if(i==4)
				valeurRatioDTO.setValeur(BigDecimal.valueOf(getRatio4(lastIndicateur)));
			else if(i==5)
				valeurRatioDTO.setValeur(BigDecimal.valueOf(getRatio5(lastIndicateur)));
			else if(i==6)
				valeurRatioDTO.setValeur(BigDecimal.valueOf(getRatio6(lastIndicateur)));
			else if(i==7)
				valeurRatioDTO.setValeur(BigDecimal.valueOf(getRatio7(lastIndicateur)));
			else if(i==8)
				valeurRatioDTO.setValeur(BigDecimal.valueOf(getRatio8(lastIndicateur)));

//			calibre la valeur ratio
			CalibrageDTO calibrageDTO = referentielService.getCalibrageByRatioAndValeurCalcule(valeurRatioDTO.getIdRatio(), valeurRatioDTO.getValeur());
			if(calibrageDTO!=null)
				valeurRatioDTO.setClasse(calibrageDTO.getClasse());
			ValeurRatio valeurRatio = modelFactory.createValeurRatio(valeurRatioDTO);
			valeurRatio = valeurRatioRepository.save(valeurRatio);
			listValeurRatio.add(valeurRatioDTO);
		}

		return listValeurRatio;
	}
	
	@Override
	public ScoresAndRatioDTO getScoreFinAndRatios(Long idDemande) throws Exception {
		ScoresAndRatioDTO scoreAndratios = new ScoresAndRatioDTO();
		scoreAndratios.setListValeurRatioDTO(calculRatios(idDemande));
		ScoresParPMEDTO scoreDTO = new ScoresParPMEDTO();
		ScoresParPME scoreparPME =  scoreParPMERepository.findScoreByDemande(idDemande);
		if(scoreparPME != null && scoreparPME.getScore_financier() != 0)
			scoreDTO = dtoFactory.createScoreParPME(scoreparPME);
		else {
			scoreDTO.setDemandeScoringDTO(demandeScoringService.getDemande(idDemande));
		}

		double score_financier = 0.0;
		double sommePonderationRatioZero = 0L;
		int nbRatioZero = 0;
		List<ValeurRatioDTO> valeurRatios = scoreAndratios.getListValeurRatioDTO();
//			get le nombre de ratio equal a zero et la somme des ponderation de ces ratios
		for(ValeurRatioDTO valeur : valeurRatios){
			if(valeur.getValeur().equals(BigDecimal.ZERO)){
				RatioDTO ratioDTO = referentielService.getRatioById(valeur.getIdRatio());
				double ponderation = ratioDTO.getPonderation() / 100.0;
				sommePonderationRatioZero += ponderation;
				nbRatioZero++;
			}
		}
		//redistribution des ponderation des ratios nulls
		double ponderationPlus = sommePonderationRatioZero / (scoreAndratios.getListValeurRatioDTO().size() - nbRatioZero);
		for(ValeurRatioDTO valeurRatio : valeurRatios){
			if(!valeurRatio.getValeur().equals(BigDecimal.ZERO)){
				RatioDTO ratioDTO = referentielService.getRatioById(valeurRatio.getIdRatio());
				double ponderation = ratioDTO.getPonderation() / 100.0;
				score_financier = score_financier + (valeurRatio.getClasse() * (ponderation + ponderationPlus));
			}
		}
//		save score financier
		scoreDTO.setScore_financier(score_financier);
		ScoresParPME score = modelFactory.createScoreParPME(scoreDTO);
		score = scoreParPMERepository.save(score);

		scoreAndratios.setScoreDTO(scoreDTO);
		return scoreAndratios;
	}

	@Override
	public List<ScoreEntrepriseParParametreDTO> calculScoreParametreQualitatif(Long id) throws CalculScoreException {
		//Entreprise entreprise = entrepriseRepository.findById(id).orElseThrow(()-> new CalculScoreException("Calcul score :: entreprise "+id+" not found."));
		DemandeScoring demandeScoring = demandeScoringRepository.findById(id).orElseThrow(()-> new CalculScoreException("Calcul score :: demande "+id+" not found."));
		List<ReponseParPME> reponseParPMEs = reponseParPMERepository.findReponseParPMEQualitatifByDemande(demandeScoring.getId());

		Map<Long, Integer> maps = calculTotalScoreForEachParametre(reponseParPMEs);
		List<ScoreEntrepriseParParametreDTO> scores = new ArrayList<>();

		for (Map.Entry<Long, Integer> map : maps.entrySet()){
			ScoreEntrepriseParParametreDTO score;

			Parametre parametre = parametreRepository.findById(map.getKey()).orElseThrow(() -> new CalculScoreException("Calcul de score :: parametre "+map.getKey()+" not found."));

			try {

				score = scoreEntrepriseParParametreService.getScoreDemandeParParametreOrNull(demandeScoring.getId(), parametre.getId());
				if (score == null){
					score = new ScoreEntrepriseParParametreDTO();
				}

				double value = (double) map.getValue() / parametre.getNbre_question();
				score.setScore(value);
				score.setParametre(dtoFactory.createParametre(parametre));
				
				score.setDemandeScoringDTO(dtoFactory.createDemandeScoring(demandeScoring));
				score = scoreEntrepriseParParametreService.saveScore(score);
				scores.add(score);

			} catch (ScoreEntrepriseParParametreException e) {
				throw new CalculScoreException(e.getMessage(), e);
			}
		}
		return scores;
	}

	@Override
	public ScoreEntrepriseParParametreDTO calculScoreParametreQualitatif(Long idDemande, Long parametreId) throws CalculScoreException {
		DemandeScoring demande = demandeScoringRepository.findById(idDemande).orElseThrow(()-> new CalculScoreException("Calcul score :: demande "+idDemande+" not found."));
		List<ReponseParPME> reponseParPMEs = reponseParPMERepository.findReponseParPMEQualitatifByDemande(demande.getId());
		List<ReponseParPME> reponses = new ArrayList<>();

		for (ReponseParPME reponse: reponseParPMEs){
			Question question = questionRepository.findById(reponse.getIdQuestion()).orElse(null);
			if(question != null && question.getParametre().getId().equals(parametreId)){
				reponses.add(reponse);
			}
		}

		return calculScoreForParametre(reponses, parametreId, idDemande);
	}

	@Override
	public List<ScoreEntrepriseParParametreDTO> getScoreDemandeParParametre(Long id) throws CalculScoreException{
		try {
			List<ScoreEntrepriseParParametreDTO> scores = scoreEntrepriseParParametreService.getScoreDemandeParParametre(id);
			return scores;
		} catch (ScoreEntrepriseParParametreException e) {
			throw new CalculScoreException("Calcul score :: "+e.getMessage());
		}
	}

	@Override
	public ScoreEntrepriseParParametreDTO getScoreDemandeParParametre(Long idDemande, Long parametreId) throws CalculScoreException{
		try {
			ScoreEntrepriseParParametreDTO scores = scoreEntrepriseParParametreService.getScoreDemandeParParametre(idDemande, parametreId);
			return scores;
		} catch (ScoreEntrepriseParParametreException e) {
			throw new CalculScoreException("Calcul score :: "+e.getMessage());
		}
	}

	@Override
	public ScoresParPMEDTO calculScoreFinale(Long id) throws CalculScoreException{
        DemandeScoring demande = demandeScoringRepository.findById(id).orElseThrow(()-> new CalculScoreException("Calcul score :: demande "+id+" not found."));
        try {
            List<ScoreEntrepriseParParametreDTO> scores = scoreEntrepriseParParametreService.getScoreDemandeParParametre(id);
            List<PonderationDTO> ponderations = referentielService.getPonderations();
            ScoresParPME scoresParPME = scoreParPMERepository.findByDemandeScoring(demande).orElseThrow(() -> new CalculScoreException("Calcul score :: vous devez d'abord calculer le score financier."));

            double value = 0;
            for(ScoreEntrepriseParParametreDTO score : scores){
            	PonderationDTO dto = referentielService.getPonderationByParametre(score.getParametre().getId());
            	value += (score.getScore() * dto.getPonderation());
            }
            value += (scoresParPME.getScore_financier() * referentielService.getPonderationScoreFinancier().getPonderation());
            value = value / 100;

            scoresParPME.setScore_final(value);
            scoreParPMERepository.save(scoresParPME);

            ScoresParPMEDTO scoresParPMEDTO = dtoFactory.createScoreParPME(scoresParPME);
            return scoresParPMEDTO;

        } catch (Exception e) {
            throw new CalculScoreException("Calcul score ::"+ e.getMessage(), e);
        }
    }

    @Override
    public ScoresParPMEDTO getScoreFinal(Long id) throws CalculScoreException{
        DemandeScoring demandeScoring = demandeScoringRepository.findById(id).orElseThrow(()-> new CalculScoreException("Calcul score :: demande "+id+" not found."));
        ScoresParPME scoresParPME = scoreParPMERepository.findByDemandeScoring(demandeScoring).orElseThrow(() -> new CalculScoreException("Calcul score :: vous devez d'abord calculer le score financier."));
        return dtoFactory.createScoreParPME(scoresParPME);
    }

	@Override
	public ScoresAndRatioDTO getScoreFinancierAndRatios(Long idDemande) {
		ScoresParPME scoresParPME = scoreParPMERepository.findScoreByDemande(idDemande);
		List<ValeurRatio> valeurRatios = valeurRatioRepository.findValeurRatioByDemande(idDemande);

		ScoresAndRatioDTO scoresAndRatio = new ScoresAndRatioDTO();
		scoresAndRatio.setScoreDTO(dtoFactory.createScoreParPME(scoresParPME));
		List<ValeurRatioDTO> listValeurRatio = new ArrayList<>();
		try {
			for(ValeurRatio val : valeurRatios){
				ValeurRatioDTO dto = dtoFactory.createValeurRatio(val);
				RatioDTO ratiodTO = referentielService.getRatioById(dto.getIdRatio());
				dto.setNomRatio(ratiodTO.getLibelle());
				dto.setCodeRatio(ratiodTO.getCode());
				listValeurRatio.add(dto);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		scoresAndRatio.setListValeurRatioDTO(listValeurRatio);
		scoresAndRatio.setId(idDemande);

		return scoresAndRatio;
	}

	//private methods
	private Map<Long, Integer> calculTotalScoreForEachParametre(List<ReponseParPME> reponses) throws CalculScoreException{
		Map<Long, Integer> maps = new HashMap<>();
		List<Parametre> parametres = parametreRepository.findAll();
		for (Parametre parametre : parametres) {
			List<Integer> scores = new ArrayList<>();
			int total = 0;
			for (ReponseParPME reponse : reponses){
				Question question = questionRepository.findById(reponse.getIdQuestion()).orElseThrow(() -> new CalculScoreException("Calcul de score :: questionEligibilite "+reponse.getIdQuestion()+" not found."));
				ReponseQualitative reponseQualitative = reponseQualitativeRepository.findById(reponse.getId_reponse_quali()).orElseThrow(() -> new CalculScoreException("Calcul de score :: reponse "+reponse.getId_reponse_quali()+" not found."));
				if (parametre.getCode().equals(question.getParametre().getCode())){
					total += reponseQualitative.getScore();
				}
			}
			maps.put(parametre.getId(), total);
		}
		return maps;
	}

	private ScoreEntrepriseParParametreDTO calculScoreForParametre(List<ReponseParPME> reponses, Long parametreId, Long idDemande) throws CalculScoreException{
		try {
			//EntrepriseDTO entreprise = entrepriseService.getEntreprise(entrepriseId);
			DemandeScoringDTO demandeDTO = demandeScoringService.getDemande(idDemande);
 			ParametreDTO parametre = referentielService.getParamtre(parametreId);
			double total = 0;
			int nombreQuestion = 0;
			for (ReponseParPME reponse : reponses){
				Question question = questionRepository.findById(reponse.getIdQuestion()).orElseThrow(() -> new CalculScoreException("Calcul de score :: questionEligibilite "+reponse.getIdQuestion()+" not found."));
				ReponseQualitative reponseQualitative = reponseQualitativeRepository.findById(reponse.getId_reponse_quali()).orElseThrow(() -> new CalculScoreException("Calcul de score :: reponse "+reponse.getId_reponse_quali()+" not found."));
				if (parametre.getCode().equals(question.getParametre().getCode()) && !reponseQualitative.getId().equals(Constante.REPONSE_NE_SAPPLIQUE_PAS)){
					total += reponseQualitative.getScore();
					nombreQuestion++;
				}
			}
			total /= nombreQuestion;
			ScoreEntrepriseParParametreDTO scoreEntrepriseParParametre = scoreEntrepriseParParametreService.getScoreDemandeParParametreOrNull(idDemande, parametreId);
			if (scoreEntrepriseParParametre == null){
				scoreEntrepriseParParametre = new ScoreEntrepriseParParametreDTO();
			}
			scoreEntrepriseParParametre.setDemandeScoringDTO(demandeDTO);
			scoreEntrepriseParParametre.setParametre(parametre);
			scoreEntrepriseParParametre.setScore(total);

			scoreEntrepriseParParametre = scoreEntrepriseParParametreService.saveScore(scoreEntrepriseParParametre);

			calculScoreFinale(idDemande);

			return scoreEntrepriseParParametre;

		} catch (Exception e) {
			throw new CalculScoreException(e.getMessage(), e);
		}
	}



}
