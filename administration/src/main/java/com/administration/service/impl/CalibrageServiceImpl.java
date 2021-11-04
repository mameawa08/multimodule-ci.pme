package com.administration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administration.dto.CalibrageDTO;
import com.administration.mapping.DTOFactory;
import com.administration.mapping.ModelFactory;
import com.administration.model.Calibrage;
import com.administration.model.Ratio;
import com.administration.payload.CalibragePayload;
import com.administration.repository.CalibrageRepository;
import com.administration.repository.RatioRepository;
import com.administration.service.ICalibrageService;




@Service("calibrageService")
public class CalibrageServiceImpl implements ICalibrageService {
	
	@Autowired
	private CalibrageRepository calibrageRepository;
	
	@Autowired
	private RatioRepository ratioRepository;
	
	@Autowired
	private DTOFactory dtoFactory;
	
	@Autowired
	private ModelFactory modelFactory;

	@Override
	public List<CalibrageDTO> getListeCalibrages() {
		List<Calibrage> calibrages = calibrageRepository.findAll();
		List<CalibrageDTO> calibragesDto = dtoFactory.createListCalibrages(calibrages);
		return calibragesDto;
	}
	
	@Override
	public List<CalibrageDTO> getListeCalibragesActif() {
		List<Calibrage> calibrages = calibrageRepository.findAllActif();
		List<CalibrageDTO> calibragesDto = dtoFactory.createListCalibrages(calibrages);
		return calibragesDto;
	}
	
	@Override
	public List<CalibrageDTO> getListeCalibragesByRatio(Long idRatio) {
		List<Calibrage> calibrages = calibrageRepository.findCalibrageByRatio(idRatio);
		List<CalibrageDTO> calibragesDto = dtoFactory.createListCalibrages(calibrages);
		return calibragesDto;
	}
	
	@Override
	public CalibrageDTO createCalibrage(CalibragePayload calibragePayload) throws Exception {
		
		if (calibragePayload.getMin() == 0 ) {
			throw new Exception("La valeur minimale est obligatoire !");
		}
		if (calibragePayload.getMax() == 0 ) {
			throw new Exception("La valeur maximale est obligatoire !");
		}
		if (calibragePayload.getClasse() == 0 ) {
			throw new Exception("La classe est obligatoire !");
		}
		
		CalibrageDTO calibrageDTO = new CalibrageDTO();
		calibrageDTO.setId(calibragePayload.getId());
		calibrageDTO.setMin(calibragePayload.getMin());
		calibrageDTO.setMax(calibragePayload.getMax());
		calibrageDTO.setClasse(calibragePayload.getClasse());
		Ratio ratio = ratioRepository.findById(calibragePayload.getIdRatio()).orElseThrow(() -> new Exception("Not found."));
		calibrageDTO.setRatioDTO(dtoFactory.createRatio(ratio));
		calibrageDTO.setActif(1);
		
		Calibrage calibrage = modelFactory.createCalibrage(calibrageDTO);
		calibrage = calibrageRepository.save(calibrage);
		return calibrageDTO;
		
	}
	
	@Override
	public boolean deleteCalibrage(Long idCalibrage) throws Exception {
		try {
			if (idCalibrage == null) {
				throw new Exception("Le calibrage à supprimer est nul !");
			}
			Calibrage c = calibrageRepository.findById(idCalibrage).orElseThrow(()-> new Exception("Not found."));
			if (c != null)
				calibrageRepository.delete(c);
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
}
