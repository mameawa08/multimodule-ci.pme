package com.scoring.services.impl;

import java.util.Date;
import java.util.List;

import com.scoring.dto.PieceJointeDTO;
import com.scoring.exceptions.PieceJointeException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.mapping.PayloadToDTO;
import com.scoring.models.Indicateur;
import com.scoring.models.PieceJointe;
import com.scoring.repository.IndicateurRepository;
import com.scoring.repository.PieceJointeRepository;
import com.scoring.services.IPieceJointeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PieceJointeServiceImpl implements IPieceJointeService {

	@Autowired
	private PieceJointeRepository pieceJointeRepository;

	@Autowired
	private DTOFactory dtoFactory;

	@Autowired
	private PayloadToDTO payloadToDTO;

	@Autowired
	private ModelFactory modelFactory;

	@Autowired
	private IndicateurRepository indicateurRepository;


	@Override
	public List<PieceJointeDTO> getPieceJointes(Long id) throws PieceJointeException{
		Indicateur indicateur = indicateurRepository.findById(id).orElseThrow(() -> new PieceJointeException("Piece jointe :: Indicateur "+id+" not found."));
		List<PieceJointe> pieceJointes = pieceJointeRepository.findByIndicateur(indicateur);
		return dtoFactory.createListPieceJointe(pieceJointes);
	}

	@Override
	public PieceJointeDTO getPieceJointe(Long id) throws PieceJointeException{
		PieceJointe pieceJointe = pieceJointeRepository.findById(id).orElseThrow(() -> new PieceJointeException("PieceJointe :: "+id+" not found."));
		return dtoFactory.createPieceJointe(pieceJointe);
	}

	@Override
	public boolean createPieceJointe(Long id, MultipartFile[] files) throws PieceJointeException{
		Indicateur indicateur = indicateurRepository.findById(id).orElseThrow(() -> new PieceJointeException("Piece jointe :: Indicateur "+id+" not found."));
		if(files != null && files.length > 0){
			for(MultipartFile file : files){
				PieceJointeDTO pieceJointe = new PieceJointeDTO();
				pieceJointe.setDateCreation(new Date());
				pieceJointe.setNomPiece(StringUtils.cleanPath(file.getOriginalFilename()));
				try {
					pieceJointe.setContenu(file.getBytes());

					PieceJointe model = modelFactory.createPieceJointe(pieceJointe);
					model.setIndicateur(indicateur);

					pieceJointeRepository.save(model);
					pieceJointe.setId(model.getId());
				} catch (Exception e) {
					throw new PieceJointeException("Piece jointe :: "+e.getMessage(), e);
				}
			}
		}
		return true;
	}

	@Override
	public boolean switchStatus(Long id) throws PieceJointeException{
		PieceJointe pieceJointe = pieceJointeRepository.findById(id).orElseThrow(() -> new PieceJointeException("PieceJointe :: "+id+" not found."));
		try {
			pieceJointe.setActif(pieceJointe.isActif() ? false : true);
			pieceJointeRepository.save(pieceJointe);
			return true;
		} catch (Exception e) {
			throw new PieceJointeException(e.getMessage(), e);
		}
	}

}
