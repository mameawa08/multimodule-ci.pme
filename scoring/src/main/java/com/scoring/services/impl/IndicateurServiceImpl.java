package com.scoring.services.impl;

import java.util.Calendar;
import java.util.List;

import com.scoring.dto.EntrepriseDTO;
import com.scoring.dto.IndicateurDTO;
import com.scoring.exceptions.EntrepriseException;
import com.scoring.exceptions.IndicateurException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.mapping.PayloadToDTO;
import com.scoring.models.Entreprise;
import com.scoring.models.Indicateur;
import com.scoring.payloads.IndicateurPayload;
import com.scoring.repository.EntrepriseRepository;
import com.scoring.repository.IndicateurRepository;
import com.scoring.services.IEntrepriseService;
import com.scoring.services.IIndicateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private IEntrepriseService entrepriseService;


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
	public List<IndicateurDTO> getIndicateursByEntreprise(Long id) throws IndicateurException{
		Entreprise entreprise = entrepriseRepository.findById(id).orElseThrow(() -> new IndicateurException("Indicateur :: Entreprise "+id+" not found."));
		List<Indicateur> indicateurs = indicateurRepository.findByEntreprise(entreprise);
		return dtoFactory.createListIndicateur(indicateurs);
	}

	@Override
	public IndicateurDTO createIndicateur(IndicateurPayload payload) throws IndicateurException{
		IndicateurDTO indicateur = null;
		if(payload.getId() != null){
			indicateur = getIndicateur(payload.getId());
		}
		else{
			indicateur = new IndicateurDTO();
			indicateur.setActif(true);
		}

		if (payload.getBkActifCirculant() == 0)
			throw new IndicateurException("L'actif circulant est obligatoire.");

		if (payload.getBtTresorerieActif() == 0)
			throw new IndicateurException("L'actif tresorerie est obligatoire.");

		if (payload.getDpPassifCirculant() == 0)
			throw new IndicateurException("Le passif circulant est obligatoire.");

		if (payload.getDtTresoreriePassif() == 0)
			throw new IndicateurException("Le passif tresorerie est obligatoire.");

		if (payload.getXiResultatNet() == 0)
			throw new IndicateurException("Le resultat net est obligatoire.");

		if (payload.getXbChiffresDaffaires() == 0)
			throw new IndicateurException("Le chiffre d'affaire est obligatoire.");

		if (payload.getBiCreanceClient() == 0)
			throw new IndicateurException("La creance client est obligatoire.");

		if (payload.getCaCapitauxPropres() == 0)
			throw new IndicateurException("Le capital propre est obligatoire.");

		if (payload.getDfTotalRessources() == 0)
			throw new IndicateurException("Le total des ressource est obligatoire.");

		if (payload.getDjDettesFournisseurs() == 0)
			throw new IndicateurException("La dette des fournisseurs est obligatoire.");

		if (payload.getRaAchats() == 0)
			throw new IndicateurException("L'achat est obligatoire.");
		
		if (payload.getXdExcedentBrutExploit() == 0)
			throw new IndicateurException("L'excédent brut est obligatoire.");
		
		if (payload.getRmChargesFinancieres() == 0)
			throw new IndicateurException("Les charges financières sont obligatoires.");
		
		if (payload.getDaEmpruntsDettes() == 0)
			throw new IndicateurException("Les emprunts et dettes financières sont obligatoires.");
		
		if (payload.getDbDettesAcquisitions() == 0)
			throw new IndicateurException("Les dettes et acquisitions sont obligatoires.");
		
		if (payload.getTkRevenusFinanciers() == 0)
			throw new IndicateurException("Les revenus financiers sont obligatoires.");
		
		if (payload.getTlReprisesDepreciations() == 0)
			throw new IndicateurException("Les reprises de provisions et dépréciations sont obligatoires.");
		
		if (payload.getTmTransfertCharges() == 0)
			throw new IndicateurException("Les transferts de charges financières sont obligatoires.");
		
		if (payload.getRqParticipations() == 0)
			throw new IndicateurException("Les participations des travailleurs sont obligatoires.");
		
		if (payload.getRsImpot() == 0)
			throw new IndicateurException("L'impôt sur le résultat est obligatoire.");

		if(payload.getAnnee() != 0 && (payload.getAnnee() < (year - 5) || payload.getAnnee() > year))
			throw new IndicateurException("L'indicateur doit au moins etre des 5 dernieres annees.");

		if(payload.getEntreprise() == 0)
			throw new IndicateurException("L'entreprise est obligatoire");

		indicateur = payloadToDTO.createIndicateur(payload, indicateur);
		indicateur.setEndettement_struct(indicateur.getDaEmpruntsDettes()
				+indicateur.getDbDettesAcquisitions());
		indicateur.setProduit_financier(indicateur.getTkRevenusFinanciers()
				+indicateur.getTlReprisesDepreciations()+indicateur.getTmTransfertCharges());
		indicateur.setCaf(indicateur.getXdExcedentBrutExploit()+indicateur.getProduit_financier()
				+indicateur.getRmChargesFinancieres()+indicateur.getRqParticipations()+indicateur.getRsImpot());
		try {
			EntrepriseDTO entreprise = entrepriseService.getEntreprise((long)payload.getEntreprise());
			indicateur.setEntreprise(entreprise);

			Indicateur model = modelFactory.createIndicateur(indicateur);

			indicateurRepository.save(model);
			indicateur.setId(model.getId());

			if(!entreprise.isIndicateurAjoute()){
				entreprise.setIndicateurAjoute(true);
				Entreprise entreprise1  = modelFactory.createEntreprise(entreprise);
				entrepriseRepository.save(entreprise1);
			}

		} catch (EntrepriseException e) {
			throw new IndicateurException("Indicateur :: L'entreprise, id "+payload.getEntreprise()+" not found.");
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
	public IndicateurDTO getLastIndicateur(Long idEntreprise) throws IndicateurException{
		IndicateurDTO indicateurDTO = dtoFactory.createIndicateur(indicateurRepository.findLastIndicateurByEntreprise(idEntreprise));
		return indicateurDTO;
	}

	@Override
	public IndicateurDTO getIndicateursByEntrepriseAndAnnee(Long id, int annee) throws IndicateurException {
		Entreprise entreprise = entrepriseRepository.findById(id).orElseThrow(() -> new IndicateurException("Indicateur :: Entreprise "+id+" not found."));
		Indicateur indicateur = indicateurRepository.findByEntrepriseAndAnnee(entreprise, annee).orElseThrow(() -> new IndicateurException("Indicateur :: Entreprise "+id+" pour annee "+annee+" not found."));
		return dtoFactory.createIndicateur(indicateur);
	}

}
