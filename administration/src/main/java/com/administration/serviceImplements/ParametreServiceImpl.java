package com.administration.serviceImplements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.administration.service.IParametreService;

@Service("parametreService")
public class ParametreServiceImpl implements IParametreService {

	@Override
	public List<ParametreDTO> getAllTypeCourriers() {
		List<TypeCourrier> ents = typeCourrierRepository.findAll();
		List<ParametreDTO> entDto = new ArrayList<ParametreDTO>();
		for (TypeCourrier ent : ents) {
			entDto.add(dtoFactory.createTypeCourrierDTO(ent));
		}
		return entDto;
	}
}
