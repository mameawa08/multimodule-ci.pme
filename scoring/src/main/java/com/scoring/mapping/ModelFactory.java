package com.scoring.mapping;

import javax.inject.Named;

import com.scoring.dto.*;
import com.scoring.models.Dirigeant;
import com.scoring.models.Entreprise;
import com.scoring.models.Indicateur;
import com.scoring.models.PieceJointe;
import com.scoring.models.Repondant;
import com.scoring.models.ReponseParPME;
import com.scoring.models.ScoresParPME;
import com.scoring.models.ValeurRatio;
import com.scoring.models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Named
public class ModelFactory {
	
	public Entreprise createEntreprise(EntrepriseDTO entreprise){
		if(entreprise == null)
			return null;
		Entreprise model = new Entreprise();
		model.setId(entreprise.getId());
		model.setRaisonSociale(entreprise.getRaisonSociale());
		model.setAnnee(entreprise.getAnnee());
		model.setIntitule(entreprise.getIntitule());
		model.setCapital(entreprise.getCapital());
		model.setDescription(entreprise.getDescription());
		model.setRegime(entreprise.getRegime());
		model.setAdresse(entreprise.getAdresse());
		model.setSiteWeb(entreprise.getSiteWeb());
		model.setLogo(entreprise.getLogo());
		model.setEligible(entreprise.isEligible());
		model.setActif(entreprise.isActif());
		model.setRepEli(entreprise.isRepEli());
		model.setRepQuali(entreprise.isRepQuali());
		model.setIndicateurAjoute(entreprise.isIndicateurAjoute());
		if(entreprise.getDirigeant() != null && entreprise.getDirigeant().getId() != null)
			model.setDirigeant(createDirigeant(entreprise.getDirigeant()));

		model.setFormeJuridique(entreprise.getFormeJur().getId());
		model.setSecteurs(entreprise.getSecteurs().stream().map(SecteurActiviteDTO::getId).collect(Collectors.toList()));

		return model;
	}

	public Dirigeant createDirigeant(DirigeantDTO dirigeant) {
		if(dirigeant == null)
			return null;
		
		Dirigeant model = new Dirigeant();
		model.setId(dirigeant.getId());
		model.setNom(dirigeant.getNom());
		model.setPrenom(dirigeant.getPrenom());
		model.setMobile(dirigeant.getMobile());
		model.setNiveau(dirigeant.getNiveau());
		model.setEmail(dirigeant.getEmail());
		model.setSexe(dirigeant.getSexe());
		model.setDate(dirigeant.getDate());
		model.setLieu(dirigeant.getLieu());
		model.setNationalite(dirigeant.getNationalite());
		model.setAdresse(dirigeant.getAdresse());
		model.setTypePiece(dirigeant.getTypePiece());
		model.setNumeroCI(dirigeant.getNumeroCI());
		model.setActif(dirigeant.isActif());

		if(dirigeant.getEntreprise() != null && dirigeant.getEntreprise().getId() != null)
			model.setEntreprise(createEntreprise(dirigeant.getEntreprise()));

		return model;
	}
	
	public Repondant createRepondant(RepondantDTO repondant){
		if(repondant == null)
			return null;
		
		Repondant model = new Repondant();
		model.setId(repondant.getId());
		model.setNom(repondant.getNom());
		model.setPrenom(repondant.getPrenom());
		model.setMobile(repondant.getMobile());
		model.setEmail(repondant.getEmail());
		model.setFonction(repondant.getFonction());
		model.setActif(repondant.isActif());
		model.setEntreprise(createEntreprise(repondant.getEntreprise()));

		return model;
	}

	public ReponseParPME createReponseParPME(ReponseParPMEDTO reponseDTO){
		if (reponseDTO == null)
			return null;
		ReponseParPME model = new ReponseParPME();
		model.setId(reponseDTO.getId());
		model.setReponse_eligibilite(reponseDTO.isReponse_eligibilite());
		model.setEntreprise(createEntreprise(reponseDTO.getEntrepriseDTO()));
		model.setIdQuestion(reponseDTO.getIdQuestion());
		model.setId_reponse_quali(reponseDTO.getId_reponse_quali());
		return model;
	}

	public Indicateur createIndicateur(IndicateurDTO indicateur){
		if(indicateur == null)
			return null;
		Indicateur model = new Indicateur();
		model.setId(indicateur.getId());
		model.setBkActifCirculant(indicateur.getBkActifCirculant());
		model.setBtTresorerieActif(indicateur.getBtTresorerieActif());
		model.setDpPassifCirculant(indicateur.getDpPassifCirculant());
		model.setDtTresoreriePassif(indicateur.getDtTresoreriePassif());
		model.setXiResultatNet(indicateur.getXiResultatNet());
		model.setXbChiffresDaffaires(indicateur.getXbChiffresDaffaires());
		model.setBiCreanceClient(indicateur.getBiCreanceClient());
		model.setCaf(indicateur.getCaf());
		model.setCaCapitauxPropres(indicateur.getCaCapitauxPropres());
		model.setDfTotalResources(indicateur.getDfTotalResources());
		model.setDjDettesFournisseurs(indicateur.getDjDettesFournisseurs());
		model.setRaAchats(indicateur.getRaAchats());
		model.setXdExcedentBrutExploit(indicateur.getXdExcedentBrutExploit());
		model.setRmChargesFinancieres(indicateur.getRmChargesFinancieres());
		model.setAnnee(indicateur.getAnnee());
		model.setActif(indicateur.isActif());
		model.setEntreprise(createEntreprise(indicateur.getEntreprise()));

		return model;
	}

	public PieceJointe createPieceJointe(PieceJointeDTO pieceJointe){
		if(pieceJointe == null)
			return null;

		PieceJointe model = new PieceJointe();
		model.setId(pieceJointe.getId());
		model.setNomPiece(pieceJointe.getNomPiece());
		model.setDateCreation(pieceJointe.getDateCreation());
		model.setContenu(pieceJointe.getContenu());
		model.setActif(pieceJointe.isActif());

		return model;
	}

	public ValeurRatio createValeurRatio(ValeurRatioDTO valeurRatioDTO){
		if (valeurRatioDTO == null)
			return null;
		ValeurRatio valeurRatio = new ValeurRatio();
		valeurRatio.setId(valeurRatioDTO.getId());
		valeurRatio.setIdRatio(valeurRatioDTO.getIdRatio());
		valeurRatio.setValeur(valeurRatioDTO.getValeur());
		valeurRatio.setClasse(valeurRatioDTO.getClasse());
		valeurRatio.setEntreprise(createEntreprise(valeurRatioDTO.getEntrepriseDTO()));

		return valeurRatio;
	}

	public List<ReponseParPME> createListReponseParPME(List<ReponseParPMEDTO> reponseParPMEDTOs) {
		if (reponseParPMEDTOs == null || reponseParPMEDTOs.size() == 0)
			return new ArrayList<>();

		return reponseParPMEDTOs.stream().map(this::createReponseParPME).collect(Collectors.toList());
	}

	public ScoresParPME createScoreParPME(ScoresParPMEDTO scoreDTO){
		if (scoreDTO == null)
			return null;
		ScoresParPME score = new ScoresParPME();
		score.setId(scoreDTO.getId());
		score.setScore_final(scoreDTO.getScore_final());
		score.setScore_financier(scoreDTO.getScore_financier());
		score.setEntreprise(createEntreprise(scoreDTO.getEntrepriseDTO()));

		return score;
	}
	
	


	public ScoreEntrepriseParParametre createScoreEntrepriseParParametre(ScoreEntrepriseParParametreDTO score){
		if (score == null)
			return null;

		ScoreEntrepriseParParametre model = new ScoreEntrepriseParParametre();
		model.setId(score.getId());
		model.setEntreprise(createEntreprise(score.getEntreprise()));
		model.setParametre(createParametre(score.getParametre()));
		model.setScore(score.getScore());

		return model;
	}
	
	public Parametre createParametre(ParametreDTO parametreDTO){
		if (parametreDTO == null) 
			return null;
		Parametre parametre = new Parametre();
		parametre.setId(parametreDTO.getId());
		parametre.setCode(parametreDTO.getCode());
		parametre.setLibelle(parametreDTO.getLibelle());
		parametre.setNbre_question(parametreDTO.getNbre_question());
		parametre.setActif(parametreDTO.getActif());

		return parametre;
	}

}
