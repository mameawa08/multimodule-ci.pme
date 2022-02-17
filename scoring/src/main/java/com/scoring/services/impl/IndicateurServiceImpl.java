package com.scoring.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scoring.dto.DemandeScoringDTO;
import com.scoring.dto.IndicateurDTO;
import com.scoring.exceptions.DemandeException;
import com.scoring.exceptions.IndicateurException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.mapping.PayloadToDTO;
import com.scoring.models.DemandeScoring;
import com.scoring.models.Entreprise;
import com.scoring.models.Indicateur;
import com.scoring.payloads.IndicateurPayload;
import com.scoring.repository.DemandeScoringRepository;
import com.scoring.repository.EntrepriseRepository;
import com.scoring.repository.IndicateurRepository;
import com.scoring.services.IDemandeScoring;
import com.scoring.services.IEntrepriseService;
import com.scoring.services.IIndicateurService;

@Service
public class IndicateurServiceImpl implements IIndicateurService {

	@Autowired
	private IndicateurRepository indicateurRepository;

	@Autowired
	private DTOFactory dtoFactory;

	@Autowired
	private PayloadToDTO payloadToDTO;

	@Autowired
	private ModelFactory modelFactory;

	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private DemandeScoringRepository demandeScoringRepository;

	@Autowired
	private IEntrepriseService entrepriseService;
	
	@Autowired
	private IDemandeScoring demandeScoringService;

	int year = Calendar.getInstance().get(Calendar.YEAR);

	@Override
	public List<IndicateurDTO> getIndicateurs() throws IndicateurException{
		List<Indicateur> indicateurs = indicateurRepository.findAll();
		return dtoFactory.createListIndicateur(indicateurs);
	}

	@Override
	public IndicateurDTO getIndicateur(Long id) throws IndicateurException{
		Indicateur indicateur = indicateurRepository.findById(id).orElseThrow(() -> new IndicateurException("Indicateur :: "+id+" not found."));
		return dtoFactory.createIndicateur(indicateur);
	}

	@Override
	public List<IndicateurDTO> getIndicateursByDemande(Long id) throws IndicateurException{
		DemandeScoring demande = demandeScoringRepository.findById(id).orElseThrow(() -> new IndicateurException("Indicateur :: Demande "+id+" not found."));
		List<Indicateur> indicateurs = indicateurRepository.findByDemandeScoringOrderByAnneeDesc(demande);
        return dtoFactory.createListIndicateur(indicateurs);
	}

	@Override
	public IndicateurDTO createIndicateur(IndicateurPayload payload) throws IndicateurException, DemandeException{
		IndicateurDTO indicateur = null;
		if(payload.getId() != null){
			indicateur = getIndicateur(payload.getId());
		}
		else{
			indicateur = new IndicateurDTO();
			indicateur.setActif(true);
		}

		if (payload.getBkActifCirculant() == 0 &&
			payload.getBtTresorerieActif() == 0 &&
			payload.getDpPassifCirculant() == 0 &&
			payload.getDtTresoreriePassif() == 0 &&
			payload.getXiResultatNet() == 0 &&
			payload.getXbChiffresDaffaires() == 0 &&
			payload.getBiCreanceClient() == 0 &&
			payload.getCaCapitauxPropres() == 0 &&
			payload.getDfTotalRessources() == 0 &&
			payload.getDjDettesFournisseurs() == 0 &&
			payload.getRaAchats() == 0 &&
			payload.getXdExcedentBrutExploit() == 0 &&
			payload.getRmChargesFinancieres() == 0 &&
			payload.getDaEmpruntsDettes() == 0 &&
			payload.getDbDettesAcquisitions() == 0 &&
			payload.getTkRevenusFinanciers() == 0 &&
			payload.getTlReprisesDepreciations() == 0 &&
			payload.getTmTransfertCharges() == 0 &&
			payload.getRqParticipations() == 0 &&
			payload.getRsImpot() == 0
		){
			throw new IndicateurException("Il faut au moins une valeur pour une année donnée.");
		}
		if(payload.getEntreprise() == 0)
			throw new IndicateurException("L'entreprise est obligatoire");

		if(payload.getIdDemande() == 0)
			throw new IndicateurException("La demande de scoring est obligatoire");

		if(payload.getAnnee() != 0 && (payload.getAnnee() < (year - 5) || payload.getAnnee() > year))
			throw new IndicateurException("L'indicateur doit au moins etre des 5 dernieres annees.");

		if(payload.getDerniereAnnee() != 0 && (payload.getDerniereAnnee() < (year - 5) || payload.getDerniereAnnee() > year))
			throw new IndicateurException("La derniere annee de l'etat financier est obligatoire.");

		indicateur = payloadToDTO.createIndicateur(payload, indicateur);
		indicateur.setEndettement_struct(indicateur.getDaEmpruntsDettes()
				+indicateur.getDbDettesAcquisitions());
		indicateur.setProduit_financier(indicateur.getTkRevenusFinanciers()
				+indicateur.getTlReprisesDepreciations()+indicateur.getTmTransfertCharges());
		indicateur.setCaf(indicateur.getXdExcedentBrutExploit()+indicateur.getProduit_financier()
				+indicateur.getRmChargesFinancieres()+indicateur.getRqParticipations()+indicateur.getRsImpot());
		try {
			DemandeScoringDTO demandeScoringDTO = demandeScoringService.getDemande(payload.getIdDemande());
			indicateur.setDemandeScoringDTO(demandeScoringDTO);

			Indicateur model = modelFactory.createIndicateur(indicateur);

			indicateurRepository.save(model);
			indicateur.setId(model.getId());

			if(!demandeScoringDTO.isIndicateurAjoute()){
				demandeScoringDTO.setIndicateurAjoute(true);
				DemandeScoring demandeScoring = modelFactory.createDemandeScoring(demandeScoringDTO);
				demandeScoringRepository.save(demandeScoring);
			}

		}  catch (DemandeException e) {
			throw new DemandeException("Demande not found.");
		}

		return indicateur;
	}

	@Override
	public boolean switchStatus(Long id) throws IndicateurException{
		Indicateur indicateur = indicateurRepository.findById(id).orElseThrow(() -> new IndicateurException("Indicateur :: "+id+" not found."));
		try {
			indicateur.setActif(indicateur.isActif() ? false : true);
			indicateurRepository.save(indicateur);
			return true;
		} catch (Exception e) {
			throw new IndicateurException(e.getMessage(), e);
		}
	}
	
	@Override
	public IndicateurDTO getLastIndicateur(Long idDemande) throws IndicateurException{
		IndicateurDTO indicateurDTO = dtoFactory.createIndicateur(indicateurRepository.findLastIndicateurByDemande(idDemande));
		return indicateurDTO;
	}

	@Override
	public IndicateurDTO getIndicateursByDemandeAndAnnee(Long id, int annee) throws IndicateurException {
		DemandeScoring demande = demandeScoringRepository.findById(id).orElseThrow(() -> new IndicateurException("Indicateur :: Demande "+id+" not found."));
		Indicateur indicateur = indicateurRepository.findByDemandeScoringAndAnnee(demande, annee).orElseThrow(() -> new IndicateurException("Indicateur :: Demande "+id+" pour annee "+annee+" not found."));
		return dtoFactory.createIndicateur(indicateur);
	}

}
