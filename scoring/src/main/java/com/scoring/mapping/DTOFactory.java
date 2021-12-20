package com.scoring.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;
import com.scoring.dto.*;
import com.scoring.exceptions.ReferentielException;
import com.scoring.models.Dirigeant;
import com.scoring.models.Entreprise;
import com.scoring.models.Indicateur;
import com.scoring.models.PieceJointe;
import com.scoring.models.Repondant;
import com.scoring.models.ReponseParPME;
import com.scoring.models.ScoresParPME;
import com.scoring.models.ValeurRatio;
import com.scoring.models.*;
import com.scoring.services.IReferentielService;
import org.springframework.beans.factory.annotation.Autowired;


@Named
public class DTOFactory {

	@Autowired
	private IReferentielService referentielService;

	public EntrepriseDTO createEntreprise(Entreprise entreprise){
		if(entreprise == null)
			return null;
		EntrepriseDTO dto = new EntrepriseDTO();
		dto.setId(entreprise.getId());
		dto.setRaisonSociale(entreprise.getRaisonSociale());
		dto.setIntitule(entreprise.getIntitule());
		dto.setAnnee(entreprise.getAnnee());
		dto.setCapital(entreprise.getCapital());
		dto.setDescription(entreprise.getDescription());
		dto.setRegime(entreprise.getRegime());
		dto.setAdresse(entreprise.getAdresse());
		dto.setSiteWeb(entreprise.getSiteWeb());
		dto.setLogo(entreprise.getLogo());
		dto.setEligible(entreprise.isEligible());
		dto.setActif(entreprise.isActif());
		dto.setRepEli(entreprise.isRepEli());
		dto.setRepQuali(entreprise.isRepQuali());
		dto.setIndicateurAjoute(entreprise.isIndicateurAjoute());
//		dto.setSecteurs(entreprise.getSecteurs());
		dto.setSecteurs(setSecteurActivite(entreprise.getSecteurs()));
//		dto.setFormeJuridique(entreprise.getFormeJuridique());
		dto.setFormeJur(setFormeJuridique(entreprise.getFormeJuridique()));

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
		dto.setXdExcedentBrutExploit(indicateur.getXdExcedentBrutExploit());
		dto.setRmChargesFinancieres(indicateur.getRmChargesFinancieres());
		dto.setDaEmpruntsDettes(indicateur.getDaEmpruntsDettes());
		dto.setDbDettesAcquisitions(indicateur.getDbDettesAcquisitions());
		dto.setTkRevenusFinanciers(indicateur.getTkRevenusFinanciers());
		dto.setTlReprisesDepreciations(indicateur.getTlReprisesDepreciations());
		dto.setTmTransfertCharges(indicateur.getTmTransfertCharges());
		dto.setRqParticipations(indicateur.getRqParticipations());
		dto.setRsImpot(indicateur.getRsImpot());
		dto.setEndettement_struct(indicateur.getEndettement_struct());
		dto.setProduit_financier(indicateur.getProduit_financier());
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

		if(pieceJointe.getIndicateur() != null){
			dto.setIndicateur(createIndicateur(pieceJointe.getIndicateur()));
		}

		if(pieceJointe.getEntreprise() != null){
			dto.setEntreprise(createEntreprise(pieceJointe.getEntreprise()));
		}

		dto.setActif(pieceJointe.isActif());

		return dto;
	}

	public List<PieceJointeDTO> createListPieceJointe(List<PieceJointe> pieceJointes){
		if(pieceJointes != null && pieceJointes.size() == 0)
			return new ArrayList<>();

		return pieceJointes.stream().map(this::createPieceJointe).collect(Collectors.toList());
	}

	public ReponseParPMEDTO createReponseParPME(ReponseParPME reponse){
		if (reponse == null)
			return null;
		ReponseParPMEDTO dto = new ReponseParPMEDTO();
		dto.setId(reponse.getId());
		dto.setReponse_eligibilite(reponse.isReponse_eligibilite());
		dto.setEntrepriseDTO(createEntreprise(reponse.getEntreprise()));
		dto.setIdQuestion(reponse.getIdQuestion());
		dto.setId_reponse_quali(reponse.getId_reponse_quali());
		return dto;
	}

	public List<ReponseParPMEDTO> createListReponseParPME(List<ReponseParPME> reponses){
		if(reponses == null || (reponses != null && reponses.size() == 0))
			return new ArrayList<>();

		return reponses.stream().map(this::createReponseParPME).collect(Collectors.toList());

	}


	private List<SecteurActiviteDTO> setSecteurActivite(List<Long> secteurs){
		if (secteurs == null || (secteurs.size() == 0))
			return new ArrayList<>();
		List<SecteurActiviteDTO> activites = new ArrayList<>();
		try {
			for (Long secteur : secteurs) {
				SecteurActiviteDTO dto = referentielService.getSecteurActivite(secteur);
				activites.add(dto);
			}
			return  activites;
		} catch (ReferentielException e) {
			e.printStackTrace();
		}
		return activites;
	}

	private FormeJuridiqueDTO setFormeJuridique(Long forme){
		if(forme==null || forme == 0)
			return  null;
		try {
			FormeJuridiqueDTO formeJuridique = referentielService.getFormeJuridique(forme);
			return formeJuridique;
		} catch (ReferentielException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ValeurRatioDTO createValeurRatio(ValeurRatio valeurRatio){
		if (valeurRatio == null)
			return null;
		ValeurRatioDTO dto = new ValeurRatioDTO();
		dto.setId(valeurRatio.getId());
		dto.setIdRatio(valeurRatio.getIdRatio());
		dto.setValeur(valeurRatio.getValeur());
		dto.setClasse(valeurRatio.getClasse());
		dto.setEntrepriseDTO(createEntreprise(valeurRatio.getEntreprise()));
		
		return dto;
	}
	
	public List<ValeurRatioDTO> createListValeurRatio(List<ValeurRatio> listValeur){
		if(listValeur == null || (listValeur != null && listValeur.size() == 0))
			return new ArrayList<>();

		return listValeur.stream().map(this::createValeurRatio).collect(Collectors.toList());

	}
	
	public ScoresParPMEDTO createScoreParPME(ScoresParPME score){
		if (score == null)
			return null;
		ScoresParPMEDTO dto = new ScoresParPMEDTO();
		dto.setId(score.getId());
		dto.setScore_final(score.getScore_final());
		dto.setScore_financier(score.getScore_financier());
		dto.setEntrepriseDTO(createEntreprise(score.getEntreprise()));
		
		return dto;
	}
	
	public List<ScoresParPMEDTO> createListScoreParPME(List<ScoresParPME> listScores){
		if(listScores == null || (listScores != null && listScores.size() == 0))
			return new ArrayList<>();

		return listScores.stream().map(this::createScoreParPME).collect(Collectors.toList());
	}

	public ScoreEntrepriseParParametreDTO createScoreEntrepriseParParametre(ScoreEntrepriseParParametre score){
		if (score == null)
			return null;

		ScoreEntrepriseParParametreDTO dto = new ScoreEntrepriseParParametreDTO();
		dto.setId(score.getId());
		dto.setEntreprise(createEntreprise(score.getEntreprise()));
		dto.setParametre(createParametre(score.getParametre()));
		dto.setScore(score.getScore());

		return dto;
	}

	public List<ScoreEntrepriseParParametreDTO> createListScoreEntrepriseParParametre(List<ScoreEntrepriseParParametre> scores) {
		if (scores == null || scores.size() == 0)
			return new ArrayList<>();

		return scores.stream().map(this::createScoreEntrepriseParParametre).collect(Collectors.toList());
	}
	
	public ParametreDTO createParametre(Parametre parametre){
		if (parametre == null)
			return null;
		ParametreDTO dto = new ParametreDTO();
		dto.setId(parametre.getId());
		dto.setCode(parametre.getCode());
		dto.setLibelle(parametre.getLibelle());
		dto.setNbre_question(parametre.getNbre_question());
		dto.setActif(parametre.getActif());

		return dto;
	}

	public QuestionDTO createQuestion(Question question){
		if (question == null)
			return null;
		QuestionDTO dto = new QuestionDTO();
		dto.setId(question.getId());
		dto.setCode(question.getCode());
		dto.setLibelle(question.getLibelle());
		dto.setActif(question.getActif());
		if(question.getParametre()!=null)
			dto.setParametreDTO(createParametre(question.getParametre()));

		return dto;
	}

	public ReponseQualitativeDTO createReponseQualitative(ReponseQualitative reponse){
		if (reponse == null)
			return null;
		ReponseQualitativeDTO dto = new ReponseQualitativeDTO();
		dto.setId(reponse.getId());
		dto.setCode(reponse.getCode());
		dto.setLibelle(reponse.getLibelle());
		dto.setActif(reponse.getActif());
		dto.setScore(reponse.getScore());
		dto.setQuestionDTO(createQuestion(reponse.getQuestion()));

		return dto;
	}
	
	public DemandeScoringDTO createDemandeScoring(DemandeScoring demandeScoring){
		if (demandeScoring == null)
			return null;
		DemandeScoringDTO dto = new DemandeScoringDTO();
		dto.setId(demandeScoring.getId());
		dto.setMotif_rejet(demandeScoring.getMotif_rejet());
		dto.setStatus(demandeScoring.getStatus());
		dto.setEntrepriseDTO(createEntreprise(demandeScoring.getEntreprise()));
		dto.setDateEnvoie(demandeScoring.getDateEnvoie());
		dto.setDateReception(demandeScoring.getDateReception());
		dto.setRapportGenere(demandeScoring.getRapportGenere());
		dto.setDateCreation(demandeScoring.getDateCreation());
		return dto;
	}
	
	public List<DemandeScoringDTO> createListDemandeScoring(List<DemandeScoring> demandes) {
		if (demandes == null || demandes.size() == 0)
			return new ArrayList<>();

		return demandes.stream().map(this::createDemandeScoring).collect(Collectors.toList());
	}

	
}
