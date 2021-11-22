package com.administration.service;

import java.util.List;

import com.administration.dto.CalibrageDTO;
import com.administration.payload.CalibragePayload;


public interface ICalibrageService {

	List<CalibrageDTO> getListeCalibrages();

	List<CalibrageDTO> getListeCalibragesActif();

	List<CalibrageDTO> getListeCalibragesByRatio(Long idRatio);

	CalibrageDTO createCalibrage(CalibragePayload calibragePayload) throws Exception;

	boolean deleteCalibrage(Long idCalibrage) throws Exception;

	CalibrageDTO getCalibragesByRatioAndValeurCalcule(Long idRatio, double valeurRatio);

	

}
