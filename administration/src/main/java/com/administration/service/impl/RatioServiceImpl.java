package com.administration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administration.dto.RatioDTO;
import com.administration.mapping.DTOFactory;
import com.administration.mapping.ModelFactory;
import com.administration.model.Ratio;
import com.administration.payload.RatioPayload;
import com.administration.repository.RatioRepository;
import com.administration.service.IRatioService;


@Service("ratioService")
public class RatioServiceImpl implements IRatioService {
	
	@Autowired
	private RatioRepository ratioRepository;
	
	@Autowired
	private DTOFactory dtoFactory;
	
	@Autowired
	private ModelFactory modelFactory;

	@Override
	public List<RatioDTO> getListeRatios() {
		List<Ratio> ratios = ratioRepository.findAll();
		List<RatioDTO> ratiosDto = dtoFactory.createListRatios(ratios);
		return ratiosDto;
	}
	
	@Override
	public List<RatioDTO> getListeRatiosActif() {
		List<Ratio> ratios = ratioRepository.findAllActif();
		List<RatioDTO> ratiosDto = dtoFactory.createListRatios(ratios);
		return ratiosDto;
	}
	
	@Override
	public RatioDTO getRatioByCode(String code) {
		Ratio ratio = ratioRepository.findRatioByCode(code);
		RatioDTO ratioDto = dtoFactory.createRatio(ratio);
		return ratioDto;
	}
	
	@Override
	public RatioDTO createRatio(RatioPayload ratioPayload) throws Exception {
		
		if (ratioPayload.getLibelle() == null || ratioPayload.getLibelle().equals("")) {
			throw new Exception("Le libellé du ratio est obligatoire !");
		}
		/*if (ratioPayload.getPonderation() == null) {
			throw new Exception("La pondération du ratio est obligatoire !");
		}*/
		if (ratioPayload.getFormule() == null || ratioPayload.getFormule().equals("")) {
			throw new Exception("La formule du ratio est obligatoire !");
		}
		
		RatioDTO ratioDTO = new RatioDTO();
		ratioDTO.setId(ratioPayload.getId());
		ratioDTO.setCode(ratioPayload.getCode());
		ratioDTO.setLibelle(ratioPayload.getLibelle());
		ratioDTO.setFormule(ratioPayload.getFormule());
		ratioDTO.setUnite(ratioPayload.getUnite());
		ratioDTO.setPonderation(ratioPayload.getPonderation());
		ratioDTO.setActif(1);
		
		Ratio ratio = modelFactory.createRatio(ratioDTO);
		ratio = ratioRepository.save(ratio);
		return ratioDTO;
		
	}
	
	@Override
	public boolean deleteRatio(Long idRatio) throws Exception {
		try {
			if (idRatio == null) {
				throw new Exception("Le ratio à supprimer est nul !");
			}
			Ratio r = ratioRepository.findById(idRatio).orElseThrow(()-> new Exception("Not found."));
			if (r != null)
				ratioRepository.delete(r);
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
}
