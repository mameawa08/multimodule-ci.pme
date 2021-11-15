package com.scoring.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
public class DTOFactory {
	
	public EntrepriseDTO createEntreprise(Entreprise entreprise){
		if(entreprise == null)
			return null;
		EntrepriseDTO dto = new EntrepriseDTO();
		dto.setId(entreprise.getId());
		dto.setRaisonSociale(entreprise.getRaisonSociale());
		dto.setAnnee(entreprise.getAnnee());
		dto.setCapital(entreprise.getCapital());
		dto.setSecteur(entreprise.getSecteur());
		dto.setDescription(entreprise.getDescription());
		dto.setRegime(entreprise.getRegime());
		dto.setAdresse(entreprise.getAdresse());
		dto.setSiteWeb(entreprise.getSiteWeb());
		dto.setLogo(entreprise.getLogo());
		dto.setEligible(entreprise.isEligible());
		dto.setActif(entreprise.isActif());

		if(entreprise.getDirigeant() != null && entreprise.getDirigeant().getId() != null)
			dto.setDirigeant(createDirigeant(entreprise.getDirigeant()));

		dto.setFormeJur(entreprise.getFormeJur());

		return dto;
	}

	public List<EntrepriseDTO> createListEntreprise(List<Entreprise> entreprises){
		if(entreprises != null && entreprises.size() == 0)
			return new ArrayList<>();
		return entreprises.stream().map(this::createEntreprise).collect(Collectors.toList());
	}

	public DirigeantDTO createDirigeant(Dirigeant dirigeant) {
		if(dirigeant == null)
			return null;
		
		DirigeantDTO dto = new DirigeantDTO();
		dto.setId(dirigeant.getId());
		dto.setNom(dirigeant.getNom());
		dto.setPrenom(dirigeant.getPrenom());
		dto.setMobile(dirigeant.getMobile());
		dto.setNiveau(dirigeant.getNiveau());
		dto.setEmail(dirigeant.getEmail());
		dto.setSexe(dirigeant.getSexe());
		dto.setDate(dirigeant.getDate());
		dto.setLieu(dirigeant.getLieu());
		dto.setNationalite(dirigeant.getNationalite());
		dto.setAdresse(dirigeant.getAdresse());
		dto.setTypePiece(dirigeant.getTypePiece());
		dto.setNumeroCI(dirigeant.getNumeroCI());
		dto.setActif(dirigeant.isActif());

		if(dirigeant.getEntreprise() != null && dirigeant.getEntreprise().getId() != null)
			dto.setEntreprise(createEntreprise(dirigeant.getEntreprise()));

		return dto;
	}

	public List<DirigeantDTO> createListDirigeant(List<Dirigeant> dirigeants){
		if(dirigeants != null && dirigeants.size() == 0)
			return new ArrayList<>();
		return dirigeants.stream().map(this::createDirigeant).collect(Collectors.toList());
	}


	public RepondantDTO createRepondant(Repondant repondant){
		if(repondant == null)
			return null;
		
		RepondantDTO dto = new RepondantDTO();
		dto.setId(repondant.getId());
		dto.setNom(repondant.getNom());
		dto.setPrenom(repondant.getPrenom());
		dto.setMobile(repondant.getMobile());
		dto.setEmail(repondant.getEmail());
		dto.setFonction(repondant.getFonction());
		dto.setActif(repondant.isActif());

		dto.setEntreprise(createEntreprise(repondant.getEntreprise()));
		return dto;
	}

	public List<RepondantDTO> createListRepondant(List<Repondant> repondants){
		if(repondants != null && repondants.size() == 0)
			return new ArrayList<>();

		return repondants.stream().map(this::createRepondant).collect(Collectors.toList());
	}

	public IndicateurDTO createIndicateur(Indicateur indicateur){
		if(indicateur == null)
			return null;
		IndicateurDTO dto = new IndicateurDTO();
		dto.setId(indicateur.getId());
		dto.setBkActifCirculant(indicateur.getBkActifCirculant());
		dto.setBtTresorerieActif(indicateur.getBtTresorerieActif());
		dto.setDpPassifCirculant(indicateur.getDpPassifCirculant());
		dto.setDtTresoreriePassif(indicateur.getDtTresoreriePassif());
		dto.setXiResultatNet(indicateur.getXiResultatNet());
		dto.setXbChiffresDaffaires(indicateur.getXbChiffresDaffaires());
		dto.setBiCreanceClient(indicateur.getBiCreanceClient());
		dto.setCaf(indicateur.getCaf());
		dto.setCaCapitauxPropres(indicateur.getCaCapitauxPropres());
		dto.setDfTotalResources(indicateur.getDfTotalResources());
		dto.setDjDettesFournisseurs(indicateur.getDjDettesFournisseurs());
		dto.setRaAchats(indicateur.getRaAchats());
		dto.setAnnee(indicateur.getAnnee());
		dto.setActif(indicateur.isActif());
		dto.setEntreprise(createEntreprise(indicateur.getEntreprise()));

		return dto;
	}

	public List<IndicateurDTO> createListIndicateur(List<Indicateur> indicateurs){
		if(indicateurs != null && indicateurs.size() == 0)
			return new ArrayList<>();
		
		return indicateurs.stream().map(this::createIndicateur).collect(Collectors.toList());
	}


	public PieceJointeDTO createPieceJointe(PieceJointe pieceJointe){
		if(pieceJointe == null)
			return null;
		
		PieceJointeDTO dto = new PieceJointeDTO();
		dto.setId(pieceJointe.getId());
		dto.setNomPiece(pieceJointe.getNomPiece());
		dto.setDateCreation(pieceJointe.getDateCreation());
		dto.setContenu(pieceJointe.getContenu());
		dto.setActif(pieceJointe.isActif());

		return dto;
	}

	public List<PieceJointeDTO> createListPieceJointe(List<PieceJointe> pieceJointes){
		if(pieceJointes != null && pieceJointes.size() == 0)
			return new ArrayList<>();
		
		return pieceJointes.stream().map(this::createPieceJointe).collect(Collectors.toList());
	}


}