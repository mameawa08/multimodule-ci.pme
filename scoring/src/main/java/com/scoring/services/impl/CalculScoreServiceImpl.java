package com.scoring.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scoring.dto.*;
import com.scoring.exceptions.CalculScoreException;
import com.scoring.exceptions.ScoreEntrepriseParParametreException;
import com.scoring.models.*;
import com.scoring.repository.*;
import com.scoring.services.IScoreEntrepriseParParametreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scoring.exceptions.IndicateurException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
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

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ReponseQualitativeRepository reponseQualitativeRepository;

	@Autowired
	private ParametreRepository parametreRepository;

	@Autowired
	private IScoreEntrepriseParParametreService scoreEntrepriseParParametreService;

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
		value=indicateurDTO.getEndettement_struct()/indicateurDTO.getCaf();
		return value;
	}
		
	public double getRatio4(IndicateurDTO indicateurDTO) throws Exception {
		double value;
		value=indicateurDTO.getCaCapitauxPropres()/indicateurDTO.getDfTotalResources();
		return value;
	}
		
	public double getRatio5(IndicateurDTO indicateurDTO) throws Exception {
		double value;
		value=(indicateurDTO.getBiCreanceClient()*360)/(indicateurDTO.getXbChiffresDaffaires()*1.18);
		return value;
	}
		
	public double getRatio6(IndicateurDTO indicateurDTO) throws Exception {
		double value;
		value=(indicateurDTO.getDjDettesFournisseurs()*360)/(indicateurDTO.getRaAchats()*1.18);
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
				valeurRatioDTO.setIdRatio(listRatioDTO.get(i-1).getId());
				RatioDTO ratioDTO = referentielService.getRatioById(valeurRatioDTO.getIdRatio());
				valeurRatioDTO.setNomRatio(ratioDTO.getLibelle());
				valeurRatioDTO.setCodeRatio(ratioDTO.getCode());
				if(i==1)
					valeurRatioDTO.setValeur(new BigDecimal(getRatio1(lastIndicateur)));
				else if(i==2)
					valeurRatioDTO.setValeur(new BigDecimal(getRatio2(lastIndicateur)));
				else if(i==3)
					valeurRatioDTO.setValeur(new BigDecimal(getRatio3(lastIndicateur)));
				else if(i==4)
					valeurRatioDTO.setValeur(new BigDecimal(getRatio4(lastIndicateur)));
				else if(i==5)
					valeurRatioDTO.setValeur(new BigDecimal(getRatio5(lastIndicateur)));
				else if(i==6)
					valeurRatioDTO.setValeur(new BigDecimal(getRatio6(lastIndicateur)));
				else if(i==7)
					valeurRatioDTO.setValeur(new BigDecimal(getRatio7(lastIndicateur)));
				else if(i==8)
					valeurRatioDTO.setValeur(new BigDecimal(getRatio8(lastIndicateur)));
				CalibrageDTO calibrageDTO = referentielService.getCalibrageByRatioAndValeurCalcule(valeurRatioDTO.getIdRatio(), valeurRatioDTO.getValeur());
				if(calibrageDTO!=null) 
					valeurRatioDTO.setClasse(calibrageDTO.getClasse());
				ValeurRatio valeurRatio = modelFactory.createValeurRatio(valeurRatioDTO);
				valeurRatio = valeurRatioRepository.save(valeurRatio);
				listValeurRatio.add(valeurRatioDTO);
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
			for(ValeurRatioDTO valeur : scoreAndratios.getListValeurRatioDTO()){
				RatioDTO ratioDTO = referentielService.getRatioById(valeur.getIdRatio());
				double ponderation = ratioDTO.getPonderation()/100.0;
				score_financier = score_financier + (valeur.getClasse()*(ponderation));
			}
			scoreDTO.setScore_financier(score_financier);
			ScoresParPME score = modelFactory.createScoreParPME(scoreDTO);
			score = scoreParPMERepository.save(score);
		}
		scoreAndratios.setScoreDTO(scoreDTO);
		return scoreAndratios;
	}

	@Override
	public List<ScoreEntrepriseParParametreDTO> calculScoreParametreQualitatif(Long id) throws CalculScoreException {
		Entreprise entreprise = entrepriseRepository.findById(id).orElseThrow(()-> new CalculScoreException("Calcul score :: entreprise "+id+" not found."));
		List<ReponseParPME> reponseParPMEs = reponseParPMERepository.findReponseParPMEQualitatifByEntreprise(entreprise.getId());

		Map<Long, Integer> maps = calculTotalScoreForEachParametre(reponseParPMEs);
		List<ScoreEntrepriseParParametreDTO> scores = new ArrayList<>();

		for (Map.Entry<Long, Integer> map : maps.entrySet()){
			ScoreEntrepriseParParametreDTO score;

			Parametre parametre = parametreRepository.findById(map.getKey()).orElseThrow(() -> new CalculScoreException("Calcul de score :: parametre "+map.getKey()+" not found."));

			try {

				score = scoreEntrepriseParParametreService.getScoreEntrepriseParParametreOrNull(entreprise.getId(), parametre.getId());
				if (score == null){
					score = new ScoreEntrepriseParParametreDTO();
				}

				double value = (double) map.getValue() / parametre.getNbre_question();
				score.setScore(value);
				score.setParametre(dtoFactory.createParametre(parametre));
				score.setEntreprise(dtoFactory.createEntreprise(entreprise));

				score = scoreEntrepriseParParametreService.saveScore(score);
				scores.add(score);

			} catch (ScoreEntrepriseParParametreException e) {
				throw new CalculScoreException(e.getMessage(), e);
			}
		}
		return scores;
	}

	@Override
	public List<ScoreEntrepriseParParametreDTO> getScoreEntrepriseParParametre(Long id) throws CalculScoreException{
		try {
			List<ScoreEntrepriseParParametreDTO> scores = scoreEntrepriseParParametreService.getScoreEntrepriseParParametre(id);
			return scores;
		} catch (ScoreEntrepriseParParametreException e) {
			throw new CalculScoreException("Calcul score :: "+e.getMessage());
		}
	}

	@Override
	public ScoresParPMEDTO calculScoreFinale(Long id) throws CalculScoreException{
        Entreprise entreprise = entrepriseRepository.findById(id).orElseThrow(()-> new CalculScoreException("Calcul score :: entreprise "+id+" not found."));
        try {
            List<ScoreEntrepriseParParametreDTO> scores = scoreEntrepriseParParametreService.getScoreEntrepriseParParametre(id);
            List<PonderationDTO> ponderations = referentielService.getPonderations();
            ScoresParPME scoresParPME = scoreParPMERepository.findByEntreprise(entreprise).orElseThrow(() -> new CalculScoreException("Calcul score :: vous devez d'abord calculer le score financier."));

            double value = 0;
            for(ScoreEntrepriseParParametreDTO score : scores){
                for(PonderationDTO ponderation : ponderations){
                    if (ponderation.getParametreDTO() != null && score.getParametre().getId() == ponderation.getParametreDTO().getId()){
                        value += (score.getScore() * ponderation.getPonderation());
                    }
                    if(ponderation.getParametreDTO() == null){
                        value += scoresParPME.getScore_financier() * ponderation.getPonderation();
                    }
                }
            }
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
        Entreprise entreprise = entrepriseRepository.findById(id).orElseThrow(()-> new CalculScoreException("Calcul score :: entreprise "+id+" not found."));
        ScoresParPME scoresParPME = scoreParPMERepository.findByEntreprise(entreprise).orElseThrow(() -> new CalculScoreException("Calcul score :: vous devez d'abord calculer le score financier."));
        return dtoFactory.createScoreParPME(scoresParPME);
    }

    //private methods
	private Map<Long, Integer> calculTotalScoreForEachParametre(List<ReponseParPME> reponses) throws CalculScoreException{
		Map<Long, Integer> maps = new HashMap<>();
		List<Parametre> parametres = parametreRepository.findAll();
		for (Parametre parametre : parametres) {
			List<Integer> scores = new ArrayList<>();
			int total = 0;
			for (ReponseParPME reponse : reponses){
				Question question = questionRepository.findById(reponse.getIdQuestion()).orElseThrow(() -> new CalculScoreException("Calcul de score :: question "+reponse.getIdQuestion()+" not found."));
				ReponseQualitative reponseQualitative = reponseQualitativeRepository.findById(reponse.getId_reponse_quali()).orElseThrow(() -> new CalculScoreException("Calcul de score :: reponse "+reponse.getId_reponse_quali()+" not found."));
				if (parametre.getCode().equals(question.getParametre().getCode())){
					total += reponseQualitative.getScore();
				}
			}
			maps.put(parametre.getId(), total);
		}
		return maps;
	}



}
