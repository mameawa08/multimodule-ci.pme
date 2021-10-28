package com.administration.service;

import java.util.List;

import com.administration.dto.ReponseQualitativeDTO;
import com.administration.payload.ReponsePayload;

public interface IReponseService {

	List<ReponseQualitativeDTO> getListeReponses();

	List<ReponseQualitativeDTO> getListeReponsesActif();

	List<ReponseQualitativeDTO> getListeReponsesByQuestion(Long idQuestion);

	ReponseQualitativeDTO createReponse(ReponsePayload reponsePayload) throws Exception;

	boolean deleteReponse(Long idReponse) throws Exception;

}
