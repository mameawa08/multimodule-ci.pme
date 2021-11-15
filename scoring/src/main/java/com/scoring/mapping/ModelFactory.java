package com.scoring.mapping;

import javax.inject.Named;

import com.scoring.dto.DirigeantDTO;
import com.scoring.dto.EntrepriseDTO;
import com.scoring.dto.IndicateurDTO;
import com.scoring.dto.PieceJointeDTO;
import com.scoring.dto.RepondantDTO;
import com.scoring.models.Dirigeant;
import com.scoring.models.Entreprise;
import com.scoring.models.Indicateur;
import com.scoring.models.PieceJointe;
import com.scoring.models.Repondant;

@Named
public class ModelFactory {
	
	public Entreprise createEntreprise(EntrepriseDTO entreprise){
		if(entreprise == null)
			return null;
		Entreprise model = new Entreprise();
		model.setId(entreprise.getId());
		model.setRaisonSociale(entreprise.getRaisonSociale());
		model.setAnnee(entreprise.getAnnee());
		model.setCapital(entreprise.getCapital());
		model.setSecteur(entreprise.getSecteur());
		model.setDescription(entreprise.getDescription());
		model.setRegime(entreprise.getRegime());
		model.setAdresse(entreprise.getAdresse());
		model.setSiteWeb(entreprise.getSiteWeb());
		model.setLogo(entreprise.getLogo());
		model.setEligible(entreprise.isEligible());
		model.setActif(entreprise.isActif());

		if(entreprise.getDirigeant() != null && entreprise.getDirigeant().getId() != null)
			model.setDirigeant(createDirigeant(entreprise.getDirigeant()));

		model.setFormeJur(entreprise.getFormeJur());

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

	
}