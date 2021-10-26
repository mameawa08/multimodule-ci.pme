package com.administration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.administration.dto.ParametreDTO;
import com.administration.mapping.DTOFactory;
import com.administration.mapping.ModelFactory;
import com.administration.model.Parametre;
import com.administration.payload.ParametrePayload;
import com.administration.repository.ParametreRepository;
import com.administration.service.IParametreService;




@Service("parametreService")
public class ParametreServiceImpl implements IParametreService {
	
	@Autowired
	private ParametreRepository parametreRepository;
	
	@Autowired
	private DTOFactory dtoFactory;
	
	@Autowired
	private ModelFactory modelFactory;

	@Override
	public List<ParametreDTO> getListeParametres() {
		List<Parametre> params = parametreRepository.findAll();
		List<ParametreDTO> paramsDto = dtoFactory.createListParametre(params);
		return paramsDto;
	}
	
	@Override
	public List<ParametreDTO> getListeParametresActifs() {
		List<Parametre> params = parametreRepository.findAllActif();
		List<ParametreDTO> paramsDto = dtoFactory.createListParametre(params);
		return paramsDto;
	}
	
	@Override
	public ParametreDTO createParametre(@RequestBody ParametrePayload parametrePayload) throws Exception {
		if (parametrePayload.getLibelle() == null || parametrePayload.getLibelle().equals("")) {
			throw new Exception("Le libellé  est obligatoire !");
		}
		if (parametrePayload.getNbreQuestion() == 0) {
			throw new Exception("Choisissez le nombre de questions associé à ce paramètre !");
		}
		if (parametrePayload.getPonderation() == null) {
			throw new Exception("La pondération est obligatoire !");
		}
		ParametreDTO parametreDTO = new ParametreDTO();
		parametreDTO.setId(parametrePayload.getId());
		parametreDTO.setCode(parametrePayload.getCode());
		parametreDTO.setLibelle(parametrePayload.getLibelle());
		parametreDTO.setNbre_question(parametrePayload.getNbreQuestion());
		parametreDTO.setPonderation(parametrePayload.getPonderation());
		if(parametreDTO.getId()==null)
			parametreDTO.setActif(1);
		
		Parametre parametre = modelFactory.createParametre(parametreDTO);
		parametre = parametreRepository.save(parametre);
		return parametreDTO;
		
	}
	
	@Override
	public boolean deleteParametre(Long idParametre) throws Exception {
		try {
			if (idParametre == null) {
				throw new Exception("Le paramètre à supprimer est nul !");
			}
			Parametre p = parametreRepository.findById(idParametre).orElseThrow(()-> new Exception("Not found."));
			if (p != null)
				parametreRepository.delete(p);
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
}
