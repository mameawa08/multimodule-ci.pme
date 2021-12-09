package com.scoring.services;

import java.util.List;

import com.scoring.dto.PieceJointeDTO;
import com.scoring.exceptions.PieceJointeException;
import com.scoring.payloads.PieceJointePayload;

import org.springframework.web.multipart.MultipartFile;

public interface IPieceJointeService {
	
	public List<PieceJointeDTO> getPieceJointes(Long id) throws PieceJointeException;
	public PieceJointeDTO getPieceJointe(Long id) throws PieceJointeException;
//	public boolean createPieceJointe(Long id, MultipartFile[] files) throws PieceJointeException;

	boolean createPieceJointe(Long id, MultipartFile[] files, boolean logo) throws PieceJointeException;

	public boolean switchStatus(Long id) throws PieceJointeException;

    boolean deleteAttachment(Long id, Long pieceId, boolean logo) throws PieceJointeException;

    boolean updateNom(Long id, String nom) throws PieceJointeException;
}
