package com.scoring.mapping;

import javax.inject.Named;

import com.scoring.dto.DirigeantDTO;
import com.scoring.dto.EntrepriseDTO;
import com.scoring.dto.IndicateurDTO;
import com.scoring.dto.PieceJointeDTO;
import com.scoring.dto.RepondantDTO;
import com.scoring.payloads.DirigeantPayload;
import com.scoring.payloads.EntreprisePayload;
import com.scoring.payloads.IndicateurPayload;
import com.scoring.payloads.PieceJointePayload;
import com.scoring.payloads.RepondantPayload;

@Named
public class PayloadToDTO {
	/**
	 * Il faut setter les secteurs d'activte et la forme juridique apres l'appel de cet methode.
	 *
	 * @param entreprise
	 * @return
	 */
	public EntrepriseDTO createEntreprise(EntreprisePayload payload, EntrepriseDTO entreprise){
		if(payload == null)
			return entreprise;
		entreprise.setId(payload.getId());
		entreprise.setRaisonSociale(payload.getRaisonSociale());
		entreprise.setAnnee(payload.getAnnee());
		entreprise.setCapital(payload.getCapital());
		entreprise.setDescription(payload.getDescription());
		entreprise.setRegime(payload.getRegime());
		entreprise.setAdresse(payload.getAdresse());
		entreprise.setSiteWeb(payload.getSiteWeb());
		entreprise.setLogo(payload.getLogo());

		return entreprise;
	}

	public DirigeantDTO createDirigeant(DirigeantPayload dirigeant) {
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

		return dto;
	}

	public RepondantDTO createRepondant(RepondantPayload repondant){
		if(repondant == null)
			return null;
		
		RepondantDTO dto = new RepondantDTO();
		dto.setId(repondant.getId());
		dto.setNom(repondant.getNom());
		dto.setPrenom(repondant.getPrenom());
		dto.setMobile(repondant.getMobile());
		dto.setEmail(repondant.getEmail());
		dto.setFonction(repondant.getFonction());
		// set entreprise after this method call
		// dto.setEntreprise(createEntreprise(repondant.getEntreprise()));
		return dto;
	}


	public IndicateurDTO createIndicateur(IndicateurPayload indicateur){
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
		dto.setCaCapitauxPropres(indicateur.getCaCapitauxPropres());
		dto.setDfTotalResources(indicateur.getDfTotalRessources());
		dto.setDjDettesFournisseurs(indicateur.getDjDettesFournisseurs());
		dto.setRaAchats(indicateur.getRaAchats());
		dto.setAnnee(indicateur.getAnnee());

		return dto;
	}

	public PieceJointeDTO createPieceJointe(PieceJointePayload pieceJointe){
		if(pieceJointe == null)
			return null;
		
		PieceJointeDTO dto = new PieceJointeDTO();
		dto.setId(pieceJointe.getId());
		dto.setNomPiece(pieceJointe.getNomPiece());
		dto.setDateCreation(pieceJointe.getDateCreation());
		dto.setContenu(pieceJointe.getContenu());

		return dto;
	}

	public IndicateurDTO createIndicateur(IndicateurPayload indicateur, IndicateurDTO indicateurDTO){
		if(indicateur == null)
			return null;
		IndicateurDTO dto = indicateurDTO;
		dto.setId(indicateur.getId());
		dto.setBkActifCirculant(indicateur.getBkActifCirculant());
		dto.setBtTresorerieActif(indicateur.getBtTresorerieActif());
		dto.setDpPassifCirculant(indicateur.getDpPassifCirculant());
		dto.setDtTresoreriePassif(indicateur.getDtTresoreriePassif());
		dto.setXiResultatNet(indicateur.getXiResultatNet());
		dto.setXbChiffresDaffaires(indicateur.getXbChiffresDaffaires());
		dto.setBiCreanceClient(indicateur.getBiCreanceClient());
		dto.setCaCapitauxPropres(indicateur.getCaCapitauxPropres());
		dto.setDfTotalResources(indicateur.getDfTotalRessources());
		dto.setDjDettesFournisseurs(indicateur.getDjDettesFournisseurs());
		dto.setRaAchats(indicateur.getRaAchats());
		dto.setXdExcedentBrutExploit(indicateur.getXdExcedentBrutExploit());
		dto.setRmChargesFinancieres(indicateur.getRmChargesFinancieres());
		dto.setRmChargesFinancieres(indicateur.getRmChargesFinancieres());
		dto.setDaEmpruntsDettes(indicateur.getDaEmpruntsDettes());
		dto.setDbDettesAcquisitions(indicateur.getDbDettesAcquisitions());
		dto.setTkRevenusFinanciers(indicateur.getTkRevenusFinanciers());
		dto.setTlReprisesDepreciations(indicateur.getTlReprisesDepreciations());
		dto.setTmTransfertCharges(indicateur.getTmTransfertCharges());
		dto.setRqParticipations(indicateur.getRqParticipations());
		dto.setRsImpot(indicateur.getRsImpot());
		dto.setAnnee(indicateur.getAnnee());
		dto.setDerniereAnnee(indicateur.getDerniereAnnee());

		

		return dto;
	}
}
